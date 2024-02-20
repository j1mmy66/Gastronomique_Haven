package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultRestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class VisitorConsoleMenu(
    private val restaurantMenuController: RestaurantMenuController,
    private val restaurantMenuPrinter: RestaurantMenuPrinter,
    private val consoleReader : Reader
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
        TODO()
    }

    private fun checkOrderStatus() {
        TODO()
    }

    private fun payBill() {
        TODO()
    }

    private fun cancelOrder() {
        TODO()
    }

    private fun leaveReview() {
        TODO()
    }

    private fun showMenu() {
        restaurantMenuPrinter.printMenu(restaurantMenuController.getMenu())
    }

    private fun addDishToOrder() {
        TODO()
    }

}