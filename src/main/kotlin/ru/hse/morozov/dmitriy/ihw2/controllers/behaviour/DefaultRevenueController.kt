package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RevenueController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RevenueDatabaseController

class DefaultRevenueController(private val revenueDatabaseController: RevenueDatabaseController)
    : RevenueController{
    override fun addToRevenue(amount: Int): Boolean {
        return revenueDatabaseController.addToRevenue(amount)
    }

    override fun getRevenue(): Int {
        return revenueDatabaseController.getRevenue()
    }
}