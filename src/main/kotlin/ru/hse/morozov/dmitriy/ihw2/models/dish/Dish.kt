package org.example.ru.hse.morozov.dmitriy.ihw2.models.dish

data class Dish(val name: String,
                val category : DishType,
                val price: Int,
                val complexity: Int,
                val amount : Int,
                val countOrdered : Int = 0
    )