package org.example.ru.hse.morozov.dmitriy.ihw2.view.menu

import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.behaviour.interfaces.RestaurantMenuController
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.services.interfaces.RegistrationService
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.DishValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.controllers.validators.interfaces.RegistrationValidator
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.Dish
import org.example.ru.hse.morozov.dmitriy.ihw2.models.dish.DishType
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.EnumPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.RestaurantMenuPrinter
import org.example.ru.hse.morozov.dmitriy.ihw2.view.readers.interfaces.Reader

class AdminConsoleMenu(
    private val restaurantMenuController: RestaurantMenuController,
    private val restaurantMenuPrinter: RestaurantMenuPrinter,
    private val registrationService: RegistrationService,
    private val registrationValidator: RegistrationValidator,
    private val enumPrinter: EnumPrinter,
    private val dishValidator: DishValidator,
    private val consoleReader : Reader

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
        handleExceptions {
            restaurantMenuPrinter.printMenu(restaurantMenuController.getMenu())
        }
    }

    private fun addDish() {
        handleExceptions {
            println("Введите название блюда:")
            val dishName = consoleReader.readStr()
            dishValidator.validateName((dishName))

            println("Укажите категорию блюда цифрой")
            enumPrinter.printEnum(DishType::class.java)
            val dishType = consoleReader.readInt()
            dishValidator.validateCategory(dishType)

            println("Укажите цену блюда")
            val price = consoleReader.readInt()
            dishValidator.validatePrice(price)

            println("Укажите сложность блюда")
            val complexity = consoleReader.readInt()
            dishValidator.validateComplexity(complexity)

            if(restaurantMenuController.addDish(Dish(dishName, DishType.entries[dishType], price, complexity))) {
                println("Блюдо: ${dishName} успешно добавлено")
            }
            else {
                println("Что-то пошло не так")
            }
        }
    }

    private fun deleteDish() {
        handleExceptions {
            println("Введите название блюда:")
            val dishName = consoleReader.readStr()
            dishValidator.validateName((dishName))

            if(restaurantMenuController.deleteDishByName(dishName)) {
                println("Блюдо удалено")
            }
            else{
                println("Что-то пошло не так")
            }
        }
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
        processOperationWithLoginAndPassword { login, password ->
            registrationService.registerUser(login, password)
            println("Вы успешно зарегистрировались")
        }
    }

    private fun processOperationWithLoginAndPassword(
        operation: (String, String) -> Unit
    ) {
        handleExceptions {
            println("Введите логин:")
            val login = consoleReader.readStr()
            registrationValidator.validateLogin(login)

            println("Введите пароль:")
            val password = consoleReader.readStr()
            registrationValidator.validatePassword(password)

            operation(login, password)
        }
    }

    private fun handleExceptions(block: () -> Unit) {
        while (true) {
            try {
                block()
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NoSuchElementException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println("Некорректное значение для чисел")
            }
            catch (e : Exception) {
                println(e.message)
            }
        }
    }

}