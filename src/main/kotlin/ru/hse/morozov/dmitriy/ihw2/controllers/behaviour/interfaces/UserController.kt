package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User

interface UserController {
    fun addUser(user: User) : Boolean

    fun getUserByUsername(username: String): User?
}