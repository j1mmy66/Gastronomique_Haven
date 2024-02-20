package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.UserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.LoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.PasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI

class DefaultLoginService(
    private val userController: UserController,
    private val passwordHasherService: PasswordHasherService
) : LoginService{
    override fun login(login: String, password: String) {
        val user = userController.getUserByUsername(login) ?: throw WrongDataException("Нет такого пользователя")

        val hashedPassword = passwordHasherService.hashPassword(password)

        if (user.password != hashedPassword) {
            throw WrongDataException("Неверный пароль")
        }
        DI.currentUserRole = user.role
    }

}