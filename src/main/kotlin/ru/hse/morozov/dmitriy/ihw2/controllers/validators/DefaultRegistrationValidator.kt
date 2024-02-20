package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator

class DefaultRegistrationValidator : RegistrationValidator {
    override fun validateLogin(login: String) {
        if (login.isEmpty())
            throw WrongDataException("Логин пустой")
    }

    override fun validatePassword(password: String) {
        if (password.isEmpty())
            throw WrongDataException("Пароль пустой")
    }
}