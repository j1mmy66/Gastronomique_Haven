package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers

import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.menu.Menu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.DishPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter

class DefaultRestaurantMenuPrinter(
    private val dishPrinter: DishPrinter
) : RestaurantMenuPrinter {
    override fun printMenu(restaurantMenu : RestaurantMenu) {
        for ((dishType, dishList) in restaurantMenu.menu) {
            println("$dishType:")
            for (dish in dishList) {
                dishPrinter.printDish(dish)
            }
            println()
        }
    }
}