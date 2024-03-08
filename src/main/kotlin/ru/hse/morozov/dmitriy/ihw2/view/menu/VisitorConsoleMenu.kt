package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.OrderController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.ReviewController

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.ReviewValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI
import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review

import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class VisitorConsoleMenu(
    private val restaurantMenuController: RestaurantMenuController,
    private val restaurantMenuPrinter: RestaurantMenuPrinter,
    private val reviewController: ReviewController,
    private val reviewValidator: ReviewValidator,
    private val consoleReader : Reader,
    private val orderController: OrderController
) : ConsoleMenu("Меню посетителя") {
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Сделать заказ", ::makeOrder),
        MenuItem("Посмотреть статус заказа", ::checkOrderStatus),
        MenuItem("Оплатить счет", ::payBill),
        MenuItem("Отменить заказ", ::cancelOrder),
        MenuItem("Оставить отзыв", ::leaveReview),
        MenuItem("Показать меню", ::showMenu),
        MenuItem("Добавить блюдо в заказ", ::addDishToOrder)
    )

    private fun makeOrder() {
        handleExceptions {
            orderController.createOrder(DI.currentUserLogin)
            showMenu()
            while (true) {
                println("Введите название блюда")
                val dishName = consoleReader.readStr()
                println("Введите количество порций")
                val amount = consoleReader.readInt()
                if (orderController.addDishToOrder(dishName, amount)) {
                    println("Блюдо добавлено в заказ")
                } else {
                    println("Что-то пошло не так")
                }
                println("Хотите добавить еще блюдо? (да/нет)")
                if (consoleReader.readStr() != "да") {
                    break
                }
            }
            println(orderController.addOrder(DI.currentOrder!!))
        }


    }

    private fun checkOrderStatus() {
        handleExceptions {
            for (i in DI.preparedOrders) {
                if (i.userName == DI.currentUserLogin) {
                    println("Заказ ${i.orderID} ${i.status}")
                }

            }
            for (i in DI.orders) {
                if (i.userName == DI.currentUserLogin) {
                    println("Заказ ${i.orderID} ${i.status}")
                }
            }
        }
    }

    private fun payBill() {
        handleExceptions {
            println("Заказ на сумму ${orderController.payBill()} оплачен")
        }
    }

    private fun cancelOrder() {
        handleExceptions {
            println("Введите номер заказа")
            val id = consoleReader.readInt()
            val order = orderController.findOrderById(id)
            if (order != null) {
                if (orderController.cancelOrder(order)) {
                    println("Заказ отменен")
                } else {
                    println("Что-то пошло не так")
                }
            } else {
                println("Заказ не найден")
            }
        }
    }

    private fun leaveReview() {
        handleExceptions {
            println("Введите название блюда:")
            val dishName = consoleReader.readStr()
            reviewValidator.validateDishName(dishName)

            println("Введите оценку от 0 до 5")
            val rating = consoleReader.readInt()
            reviewValidator.validateRating(rating)

            println("Введите отзыв")
            val content = consoleReader.readStr()
            reviewValidator.validateContent(content)

            if (reviewController.addReview(Review(rating, dishName, content))) {
                println("Отзыв успешно добавлен")
            } else {
                println("Что-то пошло не так")
            }
        }
    }



    private fun showMenu() {
        handleExceptions {
            restaurantMenuPrinter.printMenu(restaurantMenuController.getMenu())
        }
    }

    private fun addDishToOrder() {
        handleExceptions {
            println("Введите номер заказа")
            val id = consoleReader.readInt()
            println("Введите название блюда")
            val dishName = consoleReader.readStr()
            println("Введите количество порций")
            val amount = consoleReader.readInt()
            if (orderController.addDishToAcceptedOrder(id, dishName, amount)) {
                println("Блюдо добавлено в заказ")
            } else {
                println("Что-то пошло не так")
            }
        }
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NoSuchElementException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println("Некорректное значение для чисел")
            }
            catch (e : Exception) {
                println(e.message)
            }
        }
    }

}