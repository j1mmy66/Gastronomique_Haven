package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces

interface RegistrationService {

    fun registerUser(login: String, password: String) : Boolean
}