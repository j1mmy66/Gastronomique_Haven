package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces

interface RegistrationValidator {

    fun validateLogin(login: String)

    fun validatePassword(password: String)
}