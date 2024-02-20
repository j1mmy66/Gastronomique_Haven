package org.example



import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole

fun main() {

//    val userDatabaseController = DefaultUserDatabaseController()
//    val menuDatabaseController = DefaultRestaurantMenuDatabaseController()
//    val reviewDatabaseController = DefaultReviewDatabaseController()
//
//    val userController = DefaultUserController(userDatabaseController)
//    val menuController = DefaultRestaurantMenuController(menuDatabaseController)
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
            runApp()
        }
        catch (e : WrongDataException) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}

private fun runApp() {
    while (true) {
        try {
            if(DI.currentUserRole == UserRole.ADMIN) {

            }
            else {

            }

        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}