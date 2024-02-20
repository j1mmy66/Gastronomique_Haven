package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces

interface ReviewValidator {
    fun validateRating(rating : Int)

    fun validateDishName(dishName : String)

    fun validateContent(content : String)
}