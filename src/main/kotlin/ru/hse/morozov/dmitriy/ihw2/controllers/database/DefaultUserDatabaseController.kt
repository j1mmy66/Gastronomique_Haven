package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.UserDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.User
import org.example.ru.hse.morozov.dmitriy.ihw2.models.user.UserRole
import java.io.FileNotFoundException
import java.sql.*

class DefaultUserDatabaseController : UserDatabaseController{
    private var connection: Connection

    init {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:users.db")

            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "username TEXT NOT NULL," +
                        "password TEXT NOT NULL," +
                        "role TEXT NOT NULL)"
            )
            closeConnection()
        }
        catch (e : FileNotFoundException) {
            closeConnection()
            throw DatabaseInitException("UserDatabasecontroller : Error with JRPC")
        }
        catch (e : SQLException) {

            closeConnection()
            throw DatabaseInitException("UserDatabasecontroller : SQLException")
        }
    }

    override fun addUser(user: User) : Boolean {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:users.db")
            val statement: Statement = connection.createStatement()



            statement.executeUpdate(
                "INSERT INTO users (username, password, role) VALUES (" +
                        "'${user.username}', '${user.password}', '${user.role.name}')"
            )
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
            return false
        }
    }

    override fun getUserByUsername(username: String): User? {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:users.db")

            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM users WHERE username = '$username'")


            val a = genUser(resultSet)
            closeConnection()
            return a
        }
        catch (e : SQLException) {
            closeConnection()
            return null
        }

    }

    private fun genUser(resultSet: ResultSet) : User?{
        return if (resultSet.next()) {
            val storedUsername = resultSet.getString("username")
            val storedPassword = resultSet.getString("password")
            val storedRole = UserRole.valueOf(resultSet.getString("role"))
            User(storedUsername, storedPassword, storedRole)
        } else {
            null
        }
    }



    override fun closeConnection() : Boolean{
        try {
            connection.close()
            return true
        }
        catch (e : SQLException) {
            return false
        }
    }
}