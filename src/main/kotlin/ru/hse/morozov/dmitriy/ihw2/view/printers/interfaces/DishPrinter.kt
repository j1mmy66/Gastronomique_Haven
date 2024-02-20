package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish

interface DishPrinter {
    fun printDish(dish : Dish)
}