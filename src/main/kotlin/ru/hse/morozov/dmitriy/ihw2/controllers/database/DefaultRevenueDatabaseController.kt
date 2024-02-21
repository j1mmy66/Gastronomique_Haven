package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RevenueDatabaseController
import java.io.FileNotFoundException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DefaultRevenueDatabaseController : RevenueDatabaseController {

    private lateinit var connection: Connection

    init {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:revenue.db")

            // Создание таблицы, если она не существует
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS revenue (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "amount INTEGER)"
            )
        }
        catch (e : FileNotFoundException) {
            throw DatabaseInitException("RevenueDatabasecontroller : Error with JRPC")
        }
        catch (e : SQLException) {
            throw DatabaseInitException("RevenueMenuDatabasecontroller : SQLException")
        }
    }

    override fun addToRevenue(amount: Int) : Boolean {
        try{
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("UPDATE revenue SET amount = amount + $amount;")
            return true
        }
        catch (e : SQLException) {
            return false
        }

    }

    override fun getRevenue(): Int {
        try{

            var revenue = 0
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT amount FROM revenue;")

            if (resultSet.next()) {
                revenue = resultSet.getInt("amount")
            }
            return revenue
        }
        catch (e: SQLException) {
            return 0
        }
    }
}