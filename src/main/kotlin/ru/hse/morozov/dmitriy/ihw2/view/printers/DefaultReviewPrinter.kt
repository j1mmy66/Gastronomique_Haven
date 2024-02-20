package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers

import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.ReviewPrinter

class DefaultReviewPrinter : ReviewPrinter {
    override fun printReview(review: Review) {
        println("Блюдо - ${review.dishName} Оценка - ${review.rating}")
        println("Отзыв: ${review.content}")
    }
}