package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.ReviewController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.DishValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.ReviewValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.models.review.Review
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.DefaultRestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class VisitorConsoleMenu(
    private val restaurantMenuController: RestaurantMenuController,
    private val restaurantMenuPrinter: RestaurantMenuPrinter,
    private val reviewController: ReviewController,
    private val reviewValidator: ReviewValidator,
    private val consoleReader : Reader
) : ConsoleMenu("Меню посетителя") {
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Сделать заказ", ::makeOrder),
        MenuItem("Посмотреть статус заказа", ::checkOrderStatus),
        MenuItem("Оплатить счет", ::payBill),
        MenuItem("Отменить заказ", ::cancelOrder),
        MenuItem("Оставить отзыв", ::leaveReview),
        MenuItem("Показать меню", ::showMenu),
        MenuItem("Добавить блюдо в заказ", ::addDishToOrder)
    )

    private fun makeOrder() {
        TODO()
    }

    private fun checkOrderStatus() {
        TODO()
    }

    private fun payBill() {
        TODO()
    }

    private fun cancelOrder() {
        TODO()
    }

    private fun leaveReview() {
        println("Введите название блюда:")
        val dishName = consoleReader.readStr()
        reviewValidator.validateDishName(dishName)

        println("Введите оценку от 0 до 5")
        val rating = consoleReader.readInt()
        reviewValidator.validateRating(rating)

        println("Введите отзыв")
        val content = consoleReader.readStr()
        reviewValidator.validateContent(content)

        if(reviewController.addReview(Review(rating, dishName, content))) {
            println("Отзыв успешно добавлен")
        }
        else {
            println("Что-то пошло не так")
        }
    }

    private fun showMenu() {
        restaurantMenuPrinter.printMenu(restaurantMenuController.getMenu())
    }

    private fun addDishToOrder() {
        TODO()
    }

}