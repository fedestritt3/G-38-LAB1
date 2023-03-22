object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        val frequencyMap = mutableMapOf<String,Int>()
        val wordList=phrase.lowercase()
            .trim()
            .replace("[!\"#$%&()*+,-./:;<=>?@\\[\\]^_{|}~]".toRegex(), " ")
            .trim()
            .split("\\s+".toRegex())



        for(s in wordList){

            var count= frequencyMap[s]
            if( count ==null ){
                count =0
            }
            frequencyMap[removePrefixesAndSuffixes(s)]=count + 1
        }

        return frequencyMap.toMap()

    }

    private fun removePrefixesAndSuffixes(s: String): String{
        var formattedString= removePrefixes(s)
        formattedString= removeSuffixes((formattedString))
        return formattedString
    }

    private fun removePrefixes(s: String) :String{
        if(s.startsWith("'"))
            return s.removePrefix("'")
        return s
    }

    private fun removeSuffixes(s: String) :String{
        if(s.endsWith("'"))
            return s.removeSuffix("'")
        return s
    }
}
