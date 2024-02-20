package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

class AdminConsoleMenu(


) : ConsoleMenu("Меню администратора") {
    override val menuItems: List<MenuItem> = listOf(
        MenuItem("Посмотреть меню", ::showMenu ),
        MenuItem("Добавить блюдо", ::addDish),
        MenuItem("Удалить блюдо",::deleteDish),
        MenuItem("Показать отзывы", ::showReviews),
        MenuItem("Добавить количество блюд", ::addDishNumber),
        MenuItem("Показать выручку", ::showRevenue),
        MenuItem("Показать статистику", ::showStatistics),
        MenuItem("Зарегистрировать нового посетителя", ::registerNewVisitor)

    )

    private fun showMenu() {
        TODO()
    }

    private fun addDish() {
        TODO()
    }

    private fun deleteDish() {
        TODO()
    }

    private fun showReviews() {
        TODO()
    }

    private fun addDishNumber() {
        TODO()
    }

    private fun showRevenue() {
        TODO()
    }

    private fun showStatistics() {
        TODO()
    }

    private fun registerNewVisitor() {
        TODO()
    }

}