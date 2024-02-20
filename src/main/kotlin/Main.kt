package org.example



import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.DefaultAuthController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.RestaurantDefaultMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.DefaultReviewController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.DefaultUserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultRestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.DefaultUserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.DefaultPasswordHasherService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.DishType
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole

fun main() {

//    val userDatabaseController = DefaultUserDatabaseController()
//    val menuDatabaseController = DefaultRestaurantMenuDatabaseController()
//    val reviewDatabaseController = DefaultReviewDatabaseController()
//
//    val userController = DefaultUserController(userDatabaseController)
//    val menuController = RestaurantDefaultMenuController(menuDatabaseController)
//    val reviewController = DefaultReviewController(reviewDatabaseController)
//    val authController = DefaultAuthController(userController)
//    //println(userController.addUser(User("admin", DefaultPasswordHasherService().hashPassword("1234"), UserRole.ADMIN)))
//    //println(userController.getUserByUsername("oleg"))
//    //println(menuController.getMenu().menu[DishType.SALAD])
//    userDatabaseController.closeConnection()
//    menuDatabaseController.closeConnection()
//    reviewDatabaseController.closeConnection()
    start()

}

private fun start() {
    while(true) {
        try {
            DI.loginConsoleMenu.show()
            if (!DI.loginConsoleMenu.processInputIfNotExit())
                break

        }
        catch (e : WrongDataException) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}