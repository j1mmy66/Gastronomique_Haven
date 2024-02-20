package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers

import org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces.EnumPrinter

class DefaultEnumPrinter : EnumPrinter {
    override fun printEnum(enumClass: Class<out Enum<*>>) {
        val values = enumClass.enumConstants
        values.forEachIndexed { index, value ->
            println("$index. $value")
        }
    }
}