
interface IFizzBuzzLogic {
    fun displayFizzBuzz(rangeStart: Int, rangeEnd: Int)
}

class FizzBuzzPrinter : IFizzBuzzLogic {
    override fun displayFizzBuzz(rangeStart: Int, rangeEnd: Int) {
        if (rangeStart > rangeEnd || rangeStart < 1 || rangeEnd > 100) {
            throw IllegalArgumentException("El rango debe ser entre 1 y 100 y válido.")
        }

        for (num in rangeStart..rangeEnd) {
            print(determineOutput(num))
            if (num % 10 == 0 || num == rangeEnd) println() else print("\t")
        }
    }

    private fun determineOutput(value: Int): String {
        return when {
            value % 15 == 0 -> "FizzBuzz"
            value % 3 == 0 -> "Fizz"
            value % 5 == 0 -> "Buzz"
            else -> value.toString()
        }
    }
}

fun main() {
    try {
        val fizzBuzzLogic = FizzBuzzPrinter()
        fizzBuzzLogic.displayFizzBuzz(1, 100)
    } catch (ex: IllegalArgumentException) {
        println("Error: ${ex.message}")
    }
}
