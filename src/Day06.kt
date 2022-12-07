fun main() {
    fun getMarker(input: String, markerVal: Int): Int {
        var counter = 0
        while(counter + markerVal <= input.length){
            val substr = input.substring(counter, counter + markerVal)
            if(substr.toCharArray().distinct().size == substr.length){
                break
            }
            counter ++
        }
        return counter + markerVal
    }

    fun part1(input: List<String>): Int {
        return getMarker(input[0], 4)
    }

    fun part2(input: List<String>): Int {
        return getMarker(input[0], 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
//    check(part2(testInput) == 0)

    val input = readInput("Day06")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
