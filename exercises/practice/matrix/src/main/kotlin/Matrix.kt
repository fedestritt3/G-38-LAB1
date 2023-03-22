class Matrix(matrixAsString: String) {

    private lateinit var matrix: List<MutableList<Int>>

    init {
        /*
        after the construction the sequence is immediately processed
        to obtain the matrix
         */
        matrixAsString.split("\n").forEachIndexed { index, s ->
            s.split(" ").forEach {
                matrix[index].add(it.toInt())
            }
        }


    }


    fun column(colNr: Int): List<Int> {
        val col = mutableListOf<Int>()
        matrix.forEachIndexed { index, ints ->
            if (index == colNr) {
                col.add(ints[index])
            }
        }

        return col.toList()
    }


    fun row(rowNr: Int): List<Int> {
        return matrix[rowNr]
    }
}

