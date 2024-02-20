package org.example.ru.hse.morozov.dmitriy.ihw2.view.printers.interfaces

interface EnumPrinter {
    fun printEnum(enumClass: Class<out Enum<*>>)
}