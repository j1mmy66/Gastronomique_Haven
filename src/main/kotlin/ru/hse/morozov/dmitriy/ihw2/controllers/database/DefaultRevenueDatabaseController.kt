package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RevenueDatabaseController
import java.io.FileNotFoundException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DefaultRevenueDatabaseController : RevenueDatabaseController {

    private var connection: Connection

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

            //Если в таблице нет данных, то добавляем строку с нулевым значением
            val resultSet = statement.executeQuery("SELECT * FROM revenue;")
            if (!resultSet.next()) {
                statement.executeUpdate("INSERT INTO revenue (amount) VALUES (0);")
            }


            closeConnection()

        }
        catch (e : FileNotFoundException) {
            closeConnection()
            throw DatabaseInitException("RevenueDatabasecontroller : Error with JRPC")

        }
        catch (e : SQLException) {
            closeConnection()
            throw DatabaseInitException("RevenueMenuDatabasecontroller : SQLException")
        }
    }

    override fun addToRevenue(amount: Int) : Boolean {
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:revenue.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("UPDATE revenue SET amount = amount + $amount;")
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
            return false
        }

    }

    override fun getRevenue(): Int {
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:revenue.db")
            var revenue = 0
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT amount FROM revenue;")

            if (resultSet.next()) {
                revenue = resultSet.getInt("amount")
            }
            closeConnection()
            return revenue
        }
        catch (e: SQLException) {
            closeConnection()
            return 0
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