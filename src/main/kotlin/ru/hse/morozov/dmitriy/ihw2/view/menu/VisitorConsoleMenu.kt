package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

class VisitorConsoleMenu(

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
        TODO()
    }

    private fun showMenu() {
        TODO()
    }

    private fun addDishToOrder() {
        TODO()
    }

}