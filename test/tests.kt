package Testing

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Tests {
    @Test
    fun getUnicColor() {
        assertEquals(listOf(People("Казанджи Михаил очество", listOf("коричневый", "красный"))), getUnicColor(listOf("Казанджи Михаил очество:коричневый, красный")))
        assertEquals(listOf(People("Казанджи Михаил очество", listOf("коричневый", "красный")),
                People("Автухович Надежда отчество", listOf("коричневые", "сгущенка", "синий")),
                People("Евдокимова Вера отчество", listOf("сгущенка", "коричневые"))), getUnicColor(listOf("Казанджи Михаил очество:коричневый, красный",
                "Автухович Надежда отчество:Коричневые, Сгущенка, синий", "Евдокимова Вера отчество:Сгущенка, Коричневые")))
    }
}
