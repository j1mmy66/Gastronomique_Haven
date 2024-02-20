package org.example.ru.hse.morozov.dmitriy.ihw2.view.readers

class ConsoleReader : Reader{
    override fun readInt(): Int {
        var input: Int? = null
        while (input == null) {
            input = readlnOrNull()?.toIntOrNull()
        }
        return input
    }

    override fun readStr(): String {
        var input: String? = null
        while (input == null) {
            input = readlnOrNull()
        }
        return input
    }


}