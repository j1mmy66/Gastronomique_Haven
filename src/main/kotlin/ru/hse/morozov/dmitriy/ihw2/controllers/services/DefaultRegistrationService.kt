package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.UserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.BusyNameOrUserExistsException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.PasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.RegistrationService
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole

class DefaultRegistrationService(
    private val userController: UserController,
    private val passwordHasherService: PasswordHasherService
)
    : RegistrationService{
    override fun registerUser(login: String, password: String) : Boolean{
        if (userController.getUserByUsername(login) != null) {

            throw BusyNameOrUserExistsException("Есть пользователь с таким логином")
        }

        val hashedPassword = passwordHasherService.hashPassword(password)

        return userController.addUser(
            User(
                username = login,
                password = hashedPassword,
                role = UserRole.VISITOR
            )
        )

    }
}