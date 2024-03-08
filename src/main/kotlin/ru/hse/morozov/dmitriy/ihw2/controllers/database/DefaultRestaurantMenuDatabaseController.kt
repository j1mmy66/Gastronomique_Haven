package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.RestaurantMenuDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.DishType
import org.example.ru.hse.morozov.dmitriy.ihw2.models.menu.RestaurantMenu
import java.io.FileNotFoundException
import java.sql.*

class DefaultRestaurantMenuDatabaseController : RestaurantMenuDatabaseController{
    private var connection: Connection

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
                        "complexity INTEGER NOT NULL," +
                        "amount INTEGER NOT NULL," +
                        "count_ordered INTEGER NOT NULL )"
            )
            closeConnection()
        }
        catch (e : FileNotFoundException) {
            closeConnection()
            throw DatabaseInitException("MenuDatabasecontroller : Error with JRPC")
        }
        catch (e : SQLException) {
            closeConnection()
            throw DatabaseInitException("MenuDatabasecontroller : SQLException")
        }
    }

    override fun addDish(dish: Dish) : Boolean{
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "INSERT INTO menu (name, dish_type, price, complexity, amount, count_ordered) VALUES (" +
                "'${dish.name}', '${dish.category}', '${dish.price}'," +
                        " '${dish.complexity}', '${dish.amount}', '${dish.countOrdered}')"
            )
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
            return false
        }
    }

    override fun deleteDishByName(dishName : String) : Boolean{
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("DELETE FROM menu WHERE name = '$dishName'")
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
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
            val amount = resultSet.getInt("amount")
            val countOrdered = resultSet.getInt("count_ordered")

            val dish = Dish(name, dishType, price, complexity, amount, countOrdered)
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
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM menu")
            val menu = genMenu(resultSet)
            closeConnection()
            return menu


        }
        catch (e : SQLException) {

            closeConnection()
            return RestaurantMenu(mutableMapOf())
        }
    }

    override fun addDishAmount(dishName: String, amount : Int): Boolean {
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("UPDATE menu SET amount = amount + $amount WHERE name = '${dishName}'")
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
            return false
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
    override fun findDishWithMaxAmount(): String? {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT name FROM menu ORDER BY count_ordered DESC LIMIT 1")
            if (resultSet.next()) {
                val dishName = resultSet.getString("name")
                closeConnection()
                return dishName
            }
            closeConnection()
            return null
        }
        catch (e : SQLException) {
            closeConnection()
            return null
        }

    }

    override fun addOrderedAmount(dishName: String, amount: Int): Boolean {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate("UPDATE menu SET count_ordered = count_ordered + $amount WHERE name = '${dishName}'")
            closeConnection()
            return true
        }
        catch (e : SQLException) {
            closeConnection()
            return false
        }
    }

    override fun getDishByName(dishName: String): Dish? {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:menu.db")
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM menu WHERE name = '$dishName'")
            if (resultSet.next()) {
                val name = resultSet.getString("name")
                val dishType = DishType.valueOf(resultSet.getString("dish_type"))
                val price = resultSet.getInt("price")
                val complexity = resultSet.getInt("complexity")
                val amount = resultSet.getInt("amount")
                val countOrdered = resultSet.getInt("count_ordered")
                closeConnection()
                return Dish(name, dishType, price, complexity, amount, countOrdered)
            }
            closeConnection()
            return null
        }
        catch (e : SQLException) {
            closeConnection()
            return null
        }
    }
}