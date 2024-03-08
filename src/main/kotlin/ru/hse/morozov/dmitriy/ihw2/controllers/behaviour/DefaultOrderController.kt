package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.OrderController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.Order
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.OrderPriority
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.OrderStatus

class DefaultOrderController(
    private val menuDatabaseController : RestaurantMenuDatabaseController,
)
    : OrderController {


    override fun billSum(order: Order): Int {
        var sum = 0
        for (dish in order.dishes) {
            sum += dish.price
        }
        return sum
    }

    private fun timeToPrepare(order: Order): Int {
        var time = 0
        for (dish in order.dishes) {
            time += dish.complexity
        }
        return time
    }

    override fun addOrder(order: Order): Boolean {
        return DI.orders.add(order)

    }

    override fun payBill() : Int{
        var paid = 0
        for (order in DI.preparedOrders) {
            if (order.userName == DI.currentUserLogin) {
                paid += billSum(order)

                println(DI.revenueController.addToRevenue(billSum(order)))
                DI.preparedOrders.remove(order)
                return paid
            }
        }
        return 0
    }

    override fun cancelOrder(order: Order) : Boolean {
        if (order.status == OrderStatus.ACCEPTED) {
            DI.orders.remove(order)
            return true
        }
        return false
    }

    override fun findOrderById(id: Int): Order? {
        for (order in DI.orders) {
            if (order.orderID == id) {
                return order
            }
        }
        return null
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun startProcessing() {


        // Запускаем основной поток для обработки заказов
        GlobalScope.launch {
            while (true) {


                // Проверяем наличие заказов в очереди
                if (DI.orders.isNotEmpty()) {

                    val order = DI.orders.poll()
                    println("\u001B[31mНачало приготовления заказа ${order.orderID}\u001B[0m")
                    prepareOrder(order)
                    println("\u001B[31mЗаказ ${order.orderID} готов\u001B[0m")
                }

                // Ждем некоторое время перед повторной проверкой очереди
                delay(3000)
            }
        }
    }


    private suspend fun prepareOrder(order: Order) {
        order.status = OrderStatus.PROCESSING
        delay(timeToPrepare(order).toLong() * 1000)
        order.status = OrderStatus.READY
        DI.preparedOrders.offer(order)
    }

    override fun addDishToOrder(dishName: String, amount: Int): Boolean {

        val dish = menuDatabaseController.getDishByName(dishName) ?: return false

        if (dish.amount < amount) {
            return false
        }

        if(DI.currentOrder?.dishes?.add(dish) == null) {
            return false
        }
        for(i in 0 until amount -1) {
            DI.currentOrder?.dishes?.add(dish)
        }
        menuDatabaseController.addOrderedAmount(dishName, amount)
        menuDatabaseController.addDishAmount(dishName, -amount)



        return true

    }

    override fun createOrder(userName: String){
        DI.currentOrder = Order(DI.orderNumber++, userName, mutableListOf(), OrderPriority.COMMON, OrderStatus.ACCEPTED)
    }

    override fun addDishToAcceptedOrder(id: Int, dishName: String, amount: Int): Boolean {
        val dish = menuDatabaseController.getDishByName(dishName) ?: return false

        if (dish.amount < amount) {
            return false
        }
        if (findOrderById(id) == null) {
            return false
        }
        if (findOrderById(id)!!.status != OrderStatus.ACCEPTED) {
            return false
        }
        if(findOrderById(id)!!.dishes.add(dish)) {
            menuDatabaseController.addDishAmount(dishName, -amount)
            return true
        }
        return false


    }

}