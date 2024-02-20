package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.ReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review
import java.io.FileNotFoundException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class DefaultReviewDatabaseController : ReviewDatabaseController{
    private lateinit var connection: Connection

    init {
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:users.db")

            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS reviews (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "rating INTEGER  NOT NULL ," +
                        "dish_name TEXT NOT NULL," +
                        "content TEXT NOT NULL)"
            )
        }
        catch (e : FileNotFoundException) {
            throw DatabaseInitException("UserDatabasecontroller : Error with JRPC")
        }
        catch (e : SQLException) {
            throw DatabaseInitException("UserDatabasecontroller : SQLException")
        }
    }

    override fun addReview(review: Review) : Boolean {
        try {
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "INSERT INTO reviews (rating, dish_name, context) VALUES (" +
                "'${review.rating}', '${review.dishName}', '${review.content}')"
            )
            return true
        }
        catch(e : SQLException) {
            return false
        }
    }

    override fun getAllReviews() : MutableList<Review> {
        try {
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM reviews")
            return genReviewList(resultSet)
        }
        catch (e : SQLException) {
            return mutableListOf()
        }
    }

    private fun genReviewList(resultSet: ResultSet) : MutableList<Review> {
        val reviewList = mutableListOf<Review>()
        while (resultSet.next()) {
            val rating = resultSet.getInt("rating")
            val dishName = resultSet.getString("dish_name")
            val content = resultSet.getString("content")
            reviewList.addLast(Review(rating, dishName, content))
        }
        return reviewList
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