package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.LoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class LoginConsoleMenu(
    private val loginService: LoginService,
    private val registrationValidator: RegistrationValidator,
    private val consoleReader : Reader
)  : ConsoleMenu("Меню входа"){
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Войти в систему", ::doLogin)
    )

    private fun doLogin() {
        doOperationWithLoginAndPassword { login, password ->
            loginService.login(login, password)
            println("Вы успешно вошли в систему")
        }
    }

    private fun doOperationWithLoginAndPassword(
        operation: (String, String) -> Unit
    ) {
        handleExceptions {
            println("Введите логин:")
            val login = consoleReader.readStr()
            registrationValidator.validateLogin(login)

            println("Введите пароль:")
            val password = consoleReader.readStr()
            registrationValidator.validatePassword(password)

            operation(login, password)
        }
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: WrongDataException) {
                println(e.message)
            }
        }
    }
}