object Flattener {
    //every test must be runned setting the others not involved in @ignore
    //this because the Flattener is a singleton object


    private val flatList= mutableListOf<Int>()
    fun flatten(source: Collection<Any?>): List<Any> {

       for(element in source){
           when( element) {
               is Int -> flatList.add(element)

               is List<*> -> this.flatten(element)
           }

       }
        return flatList.toList()

    }
}
