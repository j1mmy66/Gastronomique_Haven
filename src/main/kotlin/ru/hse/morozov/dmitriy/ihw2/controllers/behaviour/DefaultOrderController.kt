package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.OrderController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.Order
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class DefaultOrderController(
    private val executorService: ExecutorService? = Executors.newFixedThreadPool(2),
    private val orderQueue: LinkedBlockingQueue<Order> = LinkedBlockingQueue<Order>())
    : OrderController{

    init {
        // Запускаем два потока для обработки заказов
        repeat(2) {
            executorService?.submit {
                //processOrders()
            }
        }
    }
    override fun billSum(order: Order) : Int{
        var sum = 0;
        for (dish in order.dishes) {
            sum += dish.price
        }
        return sum
    }





}