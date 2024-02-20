package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces

interface PasswordHasherService {
    fun bytesToString(bytes: ByteArray): String

    fun hashPassword(password: String): String
}