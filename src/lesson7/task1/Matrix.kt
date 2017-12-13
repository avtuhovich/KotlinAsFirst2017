@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0)
        throw IllegalArgumentException("IllegalArgumentException")
    return MatrixImpl(height, width, e)
}

fun <E> createMatrix(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix<E>(height, width, values[0][0])
    for (row in 0 until height) {
        for (column in 0 until width) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}


/**
 * Средняя сложность
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val matrixList = mutableListOf<E>()

    init {
        for (it in 0 until height)
            matrixList.add(e)
    }

    override fun get(row: Int, column: Int): E = matrixList[row * width + column]

    override fun get(cell: Cell): E = matrixList[cell.row * width + cell.column]

    override fun set(row: Int, column: Int, value: E) {
        matrixList[row * width + column] = value
    }

    override fun set(cell: Cell, value: E) {
        matrixList[cell.row * width + cell.column] = value
    }

    override fun equals(other: Any?): Boolean = TODO()


    override fun hashCode(): Int {
        var result = height
        result = result * 31 + width
        result = result * 31 + matrixList.hashCode()
        return result
    }

    override fun toString(): String = TODO()
}


