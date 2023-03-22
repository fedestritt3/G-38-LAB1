object Flattener {
    /*
    I used this schema because a recursive function require a global element in which store the value


    So is possible to use the flatten method as a recursive method by using as a Singleton object the flat list
    (But using the technique just described is impossible to run all the test at the same time without an @Ignere annotation
     so I preferred the below schema that allow to run all the test simultaneously)


    */

    fun flatten(source: Collection<Any?>): List<Any> {
        val flatList= mutableListOf<Int>()

        return recursiveFlat(source, flatList)


    }
    private fun recursiveFlat(source: Collection<Any?>, flatList: MutableList<Int>) : List<Any>{
        for(element in source){
            when( element) {
                is Int -> flatList.add(element)

                is List<*> -> this.recursiveFlat(element,flatList)
            }

        }
        return flatList.toList()
    }
}
