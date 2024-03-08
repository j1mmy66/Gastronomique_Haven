package org.example.ru.hse.morozov.dmitriy.ihw2.di

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.*
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.*
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultRestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultRevenueDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultUserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RevenueDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.ReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.UserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultLoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultPasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultRegistrationService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.LoginService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.PasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.RegistrationService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.DefaultDishValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.DefaultRegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.DefaultReviewValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.DishValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.ReviewValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.models.order.Order
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole
import org.example.ru.hse.morozov.dmitriy.ihw2.view.menu.AdminConsoleMenu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.menu.LoginConsoleMenu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.menu.VisitorConsoleMenu
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultDishPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultEnumPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultRestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultReviewPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.DishPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.EnumPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.ReviewPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.ConsoleReader
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader
import java.util.concurrent.ConcurrentLinkedQueue

object DI {

    var currentUserRole = UserRole.VISITOR

    var currentOrder : Order? = null

    var orderNumber : Int = 0

    var currentUserLogin : String = ""
    private val consoleReader: Reader
        get() = ConsoleReader()

    val loginConsoleMenu : LoginConsoleMenu
        get () =LoginConsoleMenu(
            loginService = loginService,
            registrationValidator = registrationValidator,
            consoleReader = consoleReader
        )
    val adminConsoleMenu : AdminConsoleMenu
        get() = AdminConsoleMenu(
            restaurantMenuController = restaurantMenuController,
            restaurantMenuPrinter = restaurantMenuPrinter,
            registrationService = registrationService,
            registrationValidator = registrationValidator,
            reviewController = reviewController,
            revenueController = revenueController,
            enumPrinter = enumPrinter,
            dishValidator = dishValidator,
            consoleReader = consoleReader,
            reviewPrinter = reviewPrinter
        )
    private val reviewPrinter : ReviewPrinter
        get() = DefaultReviewPrinter()
    val visitorConsoleMenu : VisitorConsoleMenu
        get() = VisitorConsoleMenu(
            restaurantMenuController = restaurantMenuController,
            restaurantMenuPrinter = restaurantMenuPrinter,
            reviewController = reviewController,
            reviewValidator = reviewValidator,
            consoleReader = consoleReader,
            orderController = orderController
        )
    val orderController : OrderController
        get() = DefaultOrderController(
            menuDatabaseController = restaurantMenuDatabaseController
        )
    private val reviewValidator : ReviewValidator
        get() = DefaultReviewValidator()

    private val dishValidator : DishValidator
        get() = DefaultDishValidator()
    private val enumPrinter : EnumPrinter
        get() = DefaultEnumPrinter()
    private val revenueDatabaseController : RevenueDatabaseController
        get() = DefaultRevenueDatabaseController()


    val revenueController : RevenueController
        get () = DefaultRevenueController(
            revenueDatabaseController = revenueDatabaseController
        )

    private val reviewController : ReviewController
        get() = DefaultReviewController(
            reviewDatabaseController = reviewDatabaseController)

    private val reviewDatabaseController : ReviewDatabaseController
        get() = DefaultReviewDatabaseController()

    private val restaurantMenuPrinter : RestaurantMenuPrinter
        get () = DefaultRestaurantMenuPrinter(
            dishPrinter = dishPrinter)

    private val dishPrinter : DishPrinter
        get () = DefaultDishPrinter()

    private val registrationService : RegistrationService
        get () = DefaultRegistrationService(
            userController = userController,
            passwordHasherService = passwordHasherService
        )

    private val restaurantMenuController : RestaurantMenuController
        get() = DefaultRestaurantMenuController(
            menuDatabaseController = restaurantMenuDatabaseController
        )

   private val restaurantMenuDatabaseController : RestaurantMenuDatabaseController
       get () = DefaultRestaurantMenuDatabaseController()

    private val loginService : LoginService
        get () = DefaultLoginService(
            userController = userController,
            passwordHasherService = passwordHasherService
        )
    var crutch = 0
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

    val orders: ConcurrentLinkedQueue<Order> = ConcurrentLinkedQueue()

    val preparedOrders: ConcurrentLinkedQueue<Order> = ConcurrentLinkedQueue()




}