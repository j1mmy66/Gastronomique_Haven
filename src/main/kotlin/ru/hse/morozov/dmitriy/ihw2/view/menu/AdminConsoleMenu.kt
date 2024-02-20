package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class AdminConsoleMenu(
    private val restaurantMenuController: RestaurantMenuController,
    private val restaurantMenuPrinter: RestaurantMenuPrinter,
    private val consoleReader : Reader

) : ConsoleMenu("Меню администратора") {
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Посмотреть меню", ::showMenu ),
        MenuItem("Добавить блюдо", ::addDish),
        MenuItem("Удалить блюдо",::deleteDish),
        MenuItem("Показать отзывы", ::showReviews),
        MenuItem("Добавить количество блюд", ::addDishNumber),
        MenuItem("Показать выручку", ::showRevenue),
        MenuItem("Показать статистику", ::showStatistics),
        MenuItem("Зарегистрировать нового посетителя", ::registerNewVisitor)

    )

    private fun showMenu() {
        restaurantMenuPrinter.printMenu(restaurantMenuController.getMenu())
    }

    private fun addDish() {

    }

    private fun deleteDish() {
        TODO()
    }

    private fun showReviews() {
        TODO()
    }

    private fun addDishNumber() {
        TODO()
    }

    private fun showRevenue() {
        TODO()
    }

    private fun showStatistics() {
        TODO()
    }

    private fun registerNewVisitor() {
        TODO()
    }

}