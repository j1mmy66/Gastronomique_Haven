package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User

interface AuthController {
    fun authenticate(username: String, password: String): User?
}