data class Dir(
    val name: String,
    val parent: Dir? = null,
    val dirs: MutableList<Dir> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
) {
    fun allDirs(): List<Dir> = dirs + dirs.flatMap { it.allDirs() }
    fun size(): Int = files.sumOf { it.size } + dirs.sumOf { it.size() }
}

data class File(val size: Int)

fun main() {
    fun prepareDir(input: List<String>): Dir {
        val root = Dir("/")

        var current = root
        input.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> current = current.parent!!
                line.startsWith("$ cd") -> current = current.dirs.first { it.name == line.substringAfter("cd ") }
                line.startsWith("dir") -> current.dirs.add(Dir(line.substringAfter("dir "), current))
                !line.contains("$") -> current.files.add(File(line.substringBefore(" ").toInt()))
            }
        }

        return root
    }

    fun part1(input: List<String>): Int {
        val root = prepareDir(input)
        return root.allDirs().map { it.size() }.filter { it < 100000 }.sum()
    }

    fun part2(input: List<String>): Int {
        val root = prepareDir(input)
        val spaceToFree = 30000000 - (70000000 - root.size())
        return root.allDirs().map { it.size() }.sorted().first { it >= spaceToFree }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
