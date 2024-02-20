package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces

import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review

interface ReviewPrinter {
    fun printReview(review : Review)
}