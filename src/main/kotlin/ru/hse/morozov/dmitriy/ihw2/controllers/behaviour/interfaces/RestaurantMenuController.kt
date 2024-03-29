package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu

interface RestaurantMenuController {
    fun addDish(dish: Dish) : Boolean

    fun deleteDishByName(dishName : String) : Boolean

    fun getMenu() : RestaurantMenu

    fun addDishAmount(dishName: String, amount : Int) : Boolean

    fun findDishWithMaxAmount(): String?

    fun getDishByName(dishName: String): Dish?

    fun addOrderedAmount(dishName: String, amount: Int) : Boolean


}