class Backpack constructor(
    val compartment1: String,
    val compartment2: String,
    val str: String = compartment1 + compartment2
) {
    fun getDupeVal(): Char {
        for (c1 in compartment1) {
            for (c2 in compartment2) {
                if (c1 == c2){
                    return c1
                }
            }
        }
        return 'a'
    }
}

fun main() {
    fun getPrio(char: Char): Int {
        val prioList = mutableListOf<Char>()
        var c: Char = 'a'
        while(c <= 'z'){
            prioList.add(c)
            ++c
        }
        c = 'A'
        while (c <= 'Z'){
            prioList.add(c)
            ++c
        }
        return prioList.indexOf(char) + 1
    }

    fun readBackpacks(input: List<String>): MutableList<Backpack> {
        val backpacks = mutableListOf<Backpack>()
        input.forEach {
            val comp1 = it.substring(0, it.length / 2)
            val comp2 = it.substring(it.length / 2)
            val backpack = Backpack(comp1, comp2)
            backpacks.add(backpack)
        }
        return backpacks
    }

    fun getGroups(backpacks: List<Backpack>): MutableList<MutableList<Backpack>> {
        val backpackGroups: MutableList<MutableList<Backpack>> = mutableListOf()

        var backpackGroup: MutableList<Backpack> = mutableListOf()
        var counter = 0
        backpacks.forEach {
            backpackGroup.add(it)
            if(counter == 2) {
                backpackGroups.add(backpackGroup)
                backpackGroup = mutableListOf()
                counter = -1
            }
            counter++
        }

        return backpackGroups
    }

    fun getTotal(backpacks: List<Backpack>): Int {
        var total = 0
        backpacks.forEach {
            total += getPrio(it.getDupeVal())
        }
        return total
    }

    fun getBadgeVal(backpacks: List<Backpack>): Int {
        for (c1 in backpacks[0].str) {
            for (c2 in backpacks[1].str){
                for (c3 in backpacks[2].str){
                    if(c1 == c2 && c2 == c3){
                        return getPrio(c1)
                    }
                }
            }
        }
        return 0
    }

    fun part1(input: List<String>): Int {
        val backpacks = readBackpacks(input)
        return getTotal(backpacks)
    }

    fun part2(input: List<String>): Int {
        val backpacks = readBackpacks(input)
        val groups = getGroups(backpacks)

        var total = 0
        groups.forEach {
            total += getBadgeVal(it)
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
