package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.AuthController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User

class DefaultAuthController(private val userManager: DefaultUserController)
    : AuthController{
    override fun authenticate(username: String, password: String): User? {
        val user = userManager.getUserByUsername(username)
        return if (user != null && user.password == password) user else null
    }

}