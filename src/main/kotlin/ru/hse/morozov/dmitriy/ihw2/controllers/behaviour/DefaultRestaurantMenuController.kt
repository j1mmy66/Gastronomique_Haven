package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu

class DefaultRestaurantMenuController(
    private val menuDatabaseController: RestaurantMenuDatabaseController
)
    : RestaurantMenuController{

    override fun addDish(dish: Dish) : Boolean {
        return menuDatabaseController.addDish(dish)
    }

    override fun deleteDishByName(dishName : String) : Boolean{
        return menuDatabaseController.deleteDishByName(dishName)
    }



    override fun getMenu() : RestaurantMenu {
        return menuDatabaseController.getMenu()
    }

    override fun addDishAmount(dishName: String, amount: Int): Boolean {
        return menuDatabaseController.addDishAmount(dishName, amount)
    }

    override fun findDishWithMaxAmount(): String? {
        return menuDatabaseController.findDishWithMaxAmount()
    }

    override fun getDishByName(dishName: String): Dish? {
        return menuDatabaseController.getDishByName(dishName)
    }

    override fun addOrderedAmount(dishName: String, amount: Int): Boolean {
        return menuDatabaseController.addOrderedAmount(dishName, amount)
    }







}