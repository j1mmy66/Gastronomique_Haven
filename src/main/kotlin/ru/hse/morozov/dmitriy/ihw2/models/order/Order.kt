package org.example.ru.hse.morozov.dmitriy.ihw2.models.order

import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish

class Order(val orderID : Int,
            val userName : Int,
            val dishes : MutableList<Dish>,
            val priority: OrderPriority,
            var status: OrderStatus
    ) {
}