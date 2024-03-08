package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.UserController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.UserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User

class DefaultUserController(private val userDataBaseController: UserDatabaseController)
    : UserController{

    override fun addUser(user: User) : Boolean {
        return userDataBaseController.addUser(user)
    }

    override fun getUserByUsername(username: String): User? {
        return userDataBaseController.getUserByUsername(username)
    }



}