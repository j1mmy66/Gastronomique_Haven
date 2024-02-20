package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu

interface RestaurantMenuDatabaseController {
    fun addDish(dish: Dish) : Boolean

    fun deleteDishByName(dishName : String) : Boolean

    fun getMenu() : RestaurantMenu

    fun closeConnection() : Boolean
}