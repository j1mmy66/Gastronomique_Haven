package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User

interface UserDatabaseController {
    fun addUser(user: User) : Boolean

    fun getUserByUsername(username: String): User?


    fun closeConnection() : Boolean
}