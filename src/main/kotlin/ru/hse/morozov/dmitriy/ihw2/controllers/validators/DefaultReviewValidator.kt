package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.ReviewValidator

class DefaultReviewValidator : ReviewValidator {
    override fun validateRating(rating: Int) {
        if (rating < 0 || rating > 5) {
            throw IllegalArgumentException("Рейтинг должен быть от 0 до 5")
        }
    }

    override fun validateDishName(dishName: String) {
        if (dishName.isEmpty()) {
            throw IllegalArgumentException("Имя блюда не может быть пустым")
        }
    }

    override fun validateContent(content: String) {
        if (content.isEmpty()) {
            throw IllegalArgumentException("Содержание не может быть пустым")
        }
    }
}