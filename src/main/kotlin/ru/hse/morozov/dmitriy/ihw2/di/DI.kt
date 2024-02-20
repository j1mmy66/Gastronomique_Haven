package org.example.ru.hse.morozov.dmitriy.ihw2.di

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.DefaultUserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.UserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultUserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.UserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultLoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultPasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.LoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.PasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.DefaultRegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole
import org.example.ru.hse.morozov.dmitriy.ihw2.view.menu.LoginConsoleMenu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.ConsoleReader
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

object DI {

    var currentUserRole = UserRole.VISITOR
    val consoleReader: Reader
        get() = ConsoleReader()

    val loginConsoleMenu : LoginConsoleMenu
        get () =LoginConsoleMenu(
            loginService = loginService,
            registrationValidator = registrationValidator,
            consoleReader = consoleReader
        )

    private val loginService : LoginService
        get () = DefaultLoginService(
            userController = userController,
            passwordHasherService = passwordHasherService
        )

    private val registrationValidator : RegistrationValidator
        get () = DefaultRegistrationValidator()

    private val passwordHasherService : PasswordHasherService
        get () = DefaultPasswordHasherService()



    private val userController : UserController
        get () = DefaultUserController(
           userDataBaseController = userDatabaseController
        )

    private val userDatabaseController : UserDatabaseController
        get () = DefaultUserDatabaseController()

}