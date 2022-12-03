data class RPSAnswer(
    val name: String,
    val keys: List<String>,
    val value: Int
)

fun main() {
    val Rock = RPSAnswer("rock", listOf("A", "X"), 1)
    val Paper = RPSAnswer("paper", listOf("B", "Y"), 2)
    val Scissors = RPSAnswer("scissors", listOf("C", "Z"), 3)

    fun inputToAnswer(input: String): RPSAnswer {
        return if(Rock.keys.contains(input)){
            Rock
        } else if(Paper.keys.contains(input)){
            Paper
        } else {
            Scissors
        }
    }

    fun readStrategyGuide(input: List<String>): MutableList<MutableList<RPSAnswer>> {
        val output = mutableListOf<MutableList<RPSAnswer>>()
        input.forEach {
            val game = mutableListOf<RPSAnswer>()
            val encryptedMoves = it.split(" ")
            game.add(inputToAnswer(encryptedMoves[0]))
            game.add(inputToAnswer(encryptedMoves[1]))
            output.add(game)
        }
        return output
    }

    fun calculateGame(opponent: RPSAnswer, player: RPSAnswer): Int {
        var score = player.value
        when (player.name) {
            "rock" -> {
                when (opponent.name) {
                    "rock" -> score += 3
                    "paper" -> score += 0
                    "scissors" -> score += 6
                }
            }
            "paper" -> {
                when (opponent.name) {
                    "rock" -> score += 6
                    "paper" -> score += 3
                    "scissors" -> score += 0
                }
            }
            "scissors" -> {
                when (opponent.name) {
                    "rock" -> score += 0
                    "paper" -> score += 6
                    "scissors" -> score += 3
                }
            }
        }
        return score
    }

    fun calculateMove(opponent: RPSAnswer, player: String): Int {
        val rock = 1
        val paper = 2
        val scissors = 3

        var score = 0
        when(player){
            "Z" -> {
                score += 6
                when (opponent.name) {
                    "rock" -> score += paper
                    "paper" -> score += scissors
                    "scissors" -> score += rock
                }
            }
            "Y" -> {
                score += 3
                when (opponent.name) {
                    "rock" -> score += rock
                    "paper" -> score += paper
                    "scissors" -> score += scissors
                }
            }
            "X" -> {
                when (opponent.name) {
                    "rock" -> score += scissors
                    "paper" -> score += rock
                    "scissors" -> score += paper
                }
            }
        }
        return score
    }

    fun part1(input: List<String>): Int {
        val strategyGuide = readStrategyGuide(input)
        var total = 0
        strategyGuide.forEach {
            total += calculateGame(it[0], it[1])
        }
        return total
    }

    fun part2(input: List<String>): Int {
        val strategyGuide = readStrategyGuide(input)
        var total = 0
        strategyGuide.forEach {
            total += calculateMove(it[0], it[1].keys[1])
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
