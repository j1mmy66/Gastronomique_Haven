package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review

interface ReviewController {
    fun addReview(review: Review) : Boolean

    fun getAllReviews() : MutableList<Review>

    fun getAverageRatingByName(dishName : String) : Double
}