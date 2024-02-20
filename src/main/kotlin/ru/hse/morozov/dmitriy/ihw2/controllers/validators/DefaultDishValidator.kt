package org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.DishValidator

class DefaultDishValidator : DishValidator {
    override fun validateName(name: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("Название блюда не может быть пустым")
        }
    }

    override fun validateCategory(category: Int) {
        if (category < 0 || category > 9) {
            throw IllegalArgumentException("Категория именуется от 0 до 9")
        }
    }

    override fun validatePrice(price: Int) {
        if (price < 0) {
            throw IllegalArgumentException("Цена не может быть отрицательной")
        }
    }

    override fun validateComplexity(complexity: Int) {
        if (complexity < 0 || complexity > 100) {
            throw IllegalArgumentException("Сложность должна принадлежать диапозону от0 до 100")
        }

    }
}