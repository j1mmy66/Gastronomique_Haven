package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers

import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.DishPrinter

class DefaultDishPrinter : DishPrinter {
    override fun printDish(dish: Dish) {
        println("${dish.name} - ${dish.price} руб - Сложность: " +
                "${dish.complexity} - Категория: ${dish.category}")
    }
}