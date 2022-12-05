fun main() {
    fun createList(input: List<String>): MutableList<Int> {
        val output = mutableListOf<Int>()
        var counter = input[0].toInt()
        while(counter <= input[1].toInt()){
            output.add(counter)
            counter++
        }
        return output
    }

    fun readPairs(input: List<String>): MutableList<MutableList<MutableList<Int>>> {
        val pairs = mutableListOf<MutableList<MutableList<Int>>>()
        input.forEach {
            val elves = it.split(",")
            val range = mutableListOf<MutableList<Int>>()
            for (elf in elves) {
                val elfRange = elf.split("-")
                range.add(createList(elfRange))
            }
            pairs.add(range)
        }
        return pairs
    }

    fun isSubset(list1: List<Int>, list2: List<Int>): Boolean {
        return (list1.containsAll(list2) || list2.containsAll(list1))
    }

    fun amountOfOverlap(list1: List<Int>, list2: List<Int>): Boolean {
        for (list1Item in list1) {
            if(list2.contains(list1Item)){
                return true
            }
        }
        for (list2Item in list2) {
            if(list1.contains(list2Item)){
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val pairs = readPairs(input)
        var counter = 0
        pairs.forEach {
            if(isSubset(it[0], it[1])){
                counter++
            }
        }

        return counter
    }

    fun part2(input: List<String>): Int {
        val pairs = readPairs(input)
        var counter = 0
        pairs.forEach {
            if(amountOfOverlap(it[0], it[1])){
                counter++
            }
        }

        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
