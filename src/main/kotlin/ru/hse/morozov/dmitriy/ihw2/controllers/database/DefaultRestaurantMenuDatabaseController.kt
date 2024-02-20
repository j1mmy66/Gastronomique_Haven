package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.DishType
import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu
import java.io.FileNotFoundException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DefaultRestaurantMenuDatabaseController : RestaurantMenuDatabaseController{
    private lateinit var connection: Connection

    init {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")

            // Создание таблицы, если она не существует
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS menu (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL ," +
                        "dish_type TEXT NOT NULL," +
                        "price INTEGER NOT NULL ," +
                        "complexity INTEGER NOT NULL)"
            )
        }
        catch (e : FileNotFoundException) {
            throw DatabaseInitException("MenuDatabasecontroller : Error with JRPC")
        }
        catch (e : SQLException) {
            throw DatabaseInitException("MenuDatabasecontroller : SQLException")
        }
    }

    override fun addDish(dish: Dish) : Boolean{
        try{
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "INSERT INTO menu (name, dish_type, price, complexity) VALUES (" +
                "'${dish.name}', '${dish.category}', '${dish.price}', '${dish.complexity}')"
            )
            return true
        }
        catch (e : SQLException) {
            return false
        }
    }

    override fun deleteDishByName(dishName : String) : Boolean{
        try{
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("DELETE FROM menu WHERE name = '$dishName'")
            return true
        }
        catch (e : SQLException) {
            return false
        }
    }

    private fun genMenu(resultSet: ResultSet) : RestaurantMenu {
        val menu : MutableMap<DishType, MutableList<Dish>> = mutableMapOf()

        while(resultSet.next()) {
            val name = resultSet.getString("name")
            val dishType = DishType.valueOf(resultSet.getString("dish_type"))
            val price = resultSet.getInt("price")
            val complexity = resultSet.getInt("complexity")

            val dish = Dish(name, dishType, price, complexity)
            if (menu.containsKey(dishType)) {
                menu[dishType]!!.add(dish)
            } else {
                menu[dishType] = mutableListOf(dish)
            }
        }
        return RestaurantMenu(menu)
    }

    override fun getMenu() : RestaurantMenu {
        try{
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM menu")
            return genMenu(resultSet)


        }
        catch (e : SQLException) {
            return RestaurantMenu(mutableMapOf())
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