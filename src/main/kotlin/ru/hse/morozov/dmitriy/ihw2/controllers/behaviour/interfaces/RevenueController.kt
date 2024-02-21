package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

interface RevenueController {
    fun addToRevenue(amount: Int) : Boolean

    fun getRevenue(): Int
}