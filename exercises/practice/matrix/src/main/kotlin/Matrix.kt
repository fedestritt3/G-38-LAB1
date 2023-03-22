class Matrix(matrixAsString: String) {

    private  var matrix: List<List<Int>>

    init {
        /*
        after the construction the sequence is immediately processed
        to obtain the matrix
         */
        val tempMatrix = mutableListOf<MutableList<Int>>()
        matrixAsString.split("\n").forEachIndexed { index, s ->

            tempMatrix.add(mutableListOf())
            s.split(" ").forEach {
                tempMatrix[index].add(it.toInt())
            }
        }
        matrix= tempMatrix.toList()



    }


    fun column(colNr: Int): List<Int> {
        val col = mutableListOf<Int>()
        matrix.forEach {
            it.forEachIndexed { index, i ->
                if(index == colNr-1){
                    col.add(i)
                }
            }
        }
        return col.toList()
        
    }


    fun row(rowNr: Int): List<Int> {
        return matrix[rowNr-1]
    }
}

