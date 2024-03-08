package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.Order

interface OrderController {

    fun billSum(order: Order) : Int

    fun addDishToOrder(dishName: String, amount: Int) : Boolean

    fun createOrder(userName: String)

    fun addOrder(order: Order): Boolean

    fun startProcessing()

    fun payBill() : Int

    fun cancelOrder(order: Order) : Boolean

    fun findOrderById(id: Int) : Order?

    fun addDishToAcceptedOrder(id: Int, dishName: String, amount: Int) : Boolean




}