package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.OrderController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.Order

class DefaultOrderController(private val order : Order)
    : OrderController{
    override fun billSum() : Int{
        var sum = 0;
        for (dish in order.dishes) {
            sum += dish.price
        }
        return sum
    }
}