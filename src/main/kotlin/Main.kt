package org.example



import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.exceptions.WrongDataException
import org.example.ru.hse.morozov.dmitriy.ihw2.di.DI
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole

fun main() {


    start()


}

private fun start() {
    DI.orderController.startProcessing()
    while(true) {
        try {
            DI.loginConsoleMenu.show()
            if (!DI.loginConsoleMenu.processInputIfNotExit())
                break
            if(DI.crutch != 1) {
                runApp()
            }
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
                DI.adminConsoleMenu.show()
                if (!DI.adminConsoleMenu.processInputIfNotExit()){
                    break
                }
            }
            else {
                DI.visitorConsoleMenu.show()
                if(!DI.visitorConsoleMenu.processInputIfNotExit()){
                    break
                }

            }

        } catch (e: Exception) {
            println("Произошла ошибка: ${e.message}")
        }
    }
}

