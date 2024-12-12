
interface INumberBase {
    val num: Int
    fun identifyType(): NumCategory
}

enum class NumCategory {
    PRIME, EVEN, ODD
}

data class PrimeNum(override val num: Int) : INumberBase {
    override fun identifyType() = NumCategory.PRIME
}

data class EvenNum(override val num: Int, val factors: List<Int>) : INumberBase {
    override fun identifyType() = NumCategory.EVEN
}

data class OddNum(override val num: Int, val factors: List<Int>) : INumberBase {
    override fun identifyType() = NumCategory.ODD
}

class RangeAnalyzer {
    fun analyzeRange(limit: Int): Map<NumCategory, List<INumberBase>> {
        if (limit <= 0) throw IllegalArgumentException("El límite debe ser un entero positivo mayor que cero.")
        
        return (1..limit).map { generateNumber(it) }
                         .groupBy { it.identifyType() }
    }

    private fun generateNumber(num: Int): INumberBase {
        return when {
            checkPrime(num) -> PrimeNum(num)
            num % 2 == 0 -> EvenNum(num, getFactors(num))
            else -> OddNum(num, getFactors(num))
        }
    }

    private fun checkPrime(num: Int): Boolean {
        if (num <= 1) return false
        return (2 until num).none { num % it == 0 }
    }

    private fun getFactors(num: Int): List<Int> {
        return (1..num).filter { num % it == 0 }
    }
}

fun main() {
    try {
        val analyzer = RangeAnalyzer()
        val categorizedNumbers = analyzer.analyzeRange(20)
        
        println("Total de primos: ${categorizedNumbers[NumCategory.PRIME]?.size ?: 0}")
        println("Total de pares: ${categorizedNumbers[NumCategory.EVEN]?.size ?: 0}")
        println("Total de impares: ${categorizedNumbers[NumCategory.ODD]?.size ?: 0}")
    } catch (ex: IllegalArgumentException) {
        println("Error: ${ex.message}")
    }
}
 