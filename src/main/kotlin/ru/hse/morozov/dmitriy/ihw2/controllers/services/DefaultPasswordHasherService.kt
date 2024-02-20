package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.PasswordHasherService
import java.security.MessageDigest

class DefaultPasswordHasherService : PasswordHasherService {
    override fun hashPassword(password: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val hashedBytes = md.digest(password.toByteArray(Charsets.UTF_8))
        return bytesToString(hashedBytes)
    }

    override fun bytesToString(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (b in bytes) {
            sb.append(String.format("%02x", b))
        }
        return sb.toString()
    }
}