package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces

interface DishValidator {
    fun validateName(name: String)

    fun validateCategory(category : Int)

    fun validatePrice(price : Int)

    fun validateComplexity(complexity : Int)
}