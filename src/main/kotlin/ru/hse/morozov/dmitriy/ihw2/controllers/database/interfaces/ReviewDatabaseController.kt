package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review

interface ReviewDatabaseController {
    fun addReview(review: Review) : Boolean

    fun getAllReviews() : MutableList<Review>

    fun closeConnection() : Boolean
}