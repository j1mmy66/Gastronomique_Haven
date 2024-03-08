package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu


interface RestaurantMenuPrinter {
    fun printMenu(restaurantMenu : RestaurantMenu)
}