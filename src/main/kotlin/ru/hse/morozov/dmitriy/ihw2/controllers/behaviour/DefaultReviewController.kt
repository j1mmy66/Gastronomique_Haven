package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.ReviewController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.database.interfaces.ReviewDatabaseController
import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review

class DefaultReviewController(private val reviewDatabaseController: ReviewDatabaseController)
    : ReviewController{
    override fun addReview(review: Review) : Boolean {
        return reviewDatabaseController.addReview(review)
    }

    override fun getAllReviews() : MutableList<Review> {
        return reviewDatabaseController.getAllReviews()
    }

    override fun getAverageRatingByName(dishName : String) : Double {
        var count  = 0
        var sumRating = 0
        for (review in getAllReviews()) {
            if (review.dishName == dishName) {
                ++count
                sumRating += review.rating
            }
        }
        return if (count == 0) {
            5.0
        } else {
            sumRating.toDouble()/count
        }
    }
 }