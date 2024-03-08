package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces

interface RevenueDatabaseController {
    fun addToRevenue(amount: Int) : Boolean

    fun getRevenue(): Int

    fun closeConnection() : Boolean
}