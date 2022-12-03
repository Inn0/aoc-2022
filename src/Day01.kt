fun main() {
    fun getElvesList(input: List<String>): MutableList<MutableList<Int>> {
        val elvesList: MutableList<MutableList<Int>> = mutableListOf()
        var elf: MutableList<Int> = mutableListOf()
        input.forEach {
            if (it == ""){
                elvesList.add(elf)
                elf = mutableListOf()
            } else {
                elf.add(it.toInt())
            }
        }
        elvesList.add(elf)
        return elvesList
    }

    fun getTotalCalories(elf: List<Int>): Int {
        var total = 0
        elf.forEach {
            total += it
        }
        return total
    }

    fun part1(input: List<String>): Int {
        val elvesList = getElvesList(input)

        var highest = 0
        elvesList.forEach {
            val current = getTotalCalories(it)
            if (current > highest){
                highest = current
            }
        }

        return highest
    }

    fun part2(input: List<String>): Int {
        val elves = getElvesList(input)
        var totalsList: MutableList<Int> = mutableListOf()

        elves.forEach {
            totalsList.add(getTotalCalories(it))
        }

        totalsList = totalsList.sortedDescending().toMutableList()
        totalsList = totalsList.take(3).toMutableList()

        var total = 0
        totalsList.forEach {
            total += it
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
