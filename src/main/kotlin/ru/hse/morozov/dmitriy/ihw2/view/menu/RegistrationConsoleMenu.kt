package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.RegistrationService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

abstract class RegistrationConsoleMenu(
    private val registrationService: RegistrationService,
    private val registrationValidator: RegistrationValidator,
    private val consoleReader: Reader
) : ConsoleMenu("Меню регистрации") {

}