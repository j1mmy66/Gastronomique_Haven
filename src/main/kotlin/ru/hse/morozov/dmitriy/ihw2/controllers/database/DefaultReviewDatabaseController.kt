package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.exceptions.DatabaseInitException
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.ReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review
import java.io.FileNotFoundException
import java.sql.*

class DefaultReviewDatabaseController : ReviewDatabaseController{
    private var connection: Connection

    init {
        try{
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:reviews.db")

            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS reviews (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "rating INTEGER  NOT NULL ," +
                        "dish_name TEXT NOT NULL," +
                        "content TEXT NOT NULL)"
            )
            closeConnection()
        }
        catch (e : FileNotFoundException) {
            closeConnection()
            throw DatabaseInitException("UserDatabaseController : Error with JRPC")
        }
        catch (e : SQLException) {
            closeConnection()
            throw DatabaseInitException("UserDatabaseController : SQLException")
        }
    }

    override fun addReview(review: Review) : Boolean {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:reviews.db")
            val statement: Statement = connection.createStatement()
            statement.executeUpdate(
                "INSERT INTO reviews (rating, dish_name, content) VALUES (" +
                "'${review.rating}', '${review.dishName}', '${review.content}')"
            )
            closeConnection()
            return true
        }
        catch(e : SQLException) {
            closeConnection()
            return false
        }
    }

    override fun getAllReviews() : MutableList<Review> {
        try {
            Class.forName("org.sqlite.JDBC")
            connection = DriverManager.getConnection("jdbc:sqlite:reviews.db")
            val statement: Statement = connection.createStatement()
            val resultSet = statement.executeQuery("SELECT * FROM reviews")
            val reviews = genReviewList(resultSet)
            closeConnection()
            return reviews
        }
        catch (e : SQLException) {
            closeConnection()
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