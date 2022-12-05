data class Move(
    val amount: Int,
    val from: Int,
    val to: Int
)

fun main() {
    fun getCargo(input: List<String>): MutableList<MutableList<Char>> {
        val inputCargo = input.subList(0, input.indexOf(""))
        val cleanedInputCargo = mutableListOf<String>()
        var inputSize = 0
        inputCargo.forEach {
            if(it.contains("[")){
                var cargoString = it.replace("    ", "x")
                cargoString = cargoString.replace(" ", "")
                cargoString = cargoString.replace("[", "")
                cargoString = cargoString.replace("]", "")
                if(inputSize < cargoString.length){
                    inputSize = cargoString.length
                }
                cleanedInputCargo.add(cargoString)
            }
        }

        val outputCargo = mutableListOf<String>()
        cleanedInputCargo.forEach {
            var newString = it
            if(it.length < inputSize){
                newString += "x".repeat(inputSize - it.length)
            }
            outputCargo.add(newString)
        }

        val cargoLists: MutableList<MutableList<Char>> = mutableListOf()
        outputCargo.forEach {
            cargoLists.add(it.toList().toMutableList())
        }

        val returnCargoList: MutableList<MutableList<Char>> = MutableList(inputSize) { mutableListOf() }
        cargoLists.forEach {
            for(i in 0 until inputSize) {
                returnCargoList[i].add(it[i])
            }
        }

        repeat(inputSize) {
            returnCargoList.forEach {
                it.remove('x')
            }
        }

        return returnCargoList
    }

    fun getMoves(input: List<String>): MutableList<Move> {
        val inputMoves = input.subList(input.indexOf("") + 1, input.size)
        val cleanedMoves = mutableListOf<Move>()
        inputMoves.forEach {
            var str = it.replace("move ", "")
            str = str.replace("from ", "")
            str = str.replace("to ", "")
            val arr = str.split(" ")
            cleanedMoves.add(Move(arr[0].toInt(), arr[1].toInt(), arr[2].toInt()))
        }

        return cleanedMoves
    }

    fun part1(input: List<String>): String {
        val cargo = getCargo(input)
        val moves = getMoves(input)
        for(move in moves){
            repeat(move.amount){
                val c = cargo[move.from - 1][0]
                cargo[move.from - 1].removeAt(0)
                cargo[move.to - 1].add(0, c)
            }
        }
        var returnStr = ""
        cargo.forEach {
            returnStr += it[0]
        }
        return returnStr
    }

    fun part2(input: List<String>): String {
        val cargo = getCargo(input)
        val moves = getMoves(input)
        for(move in moves){
            val listToAppend = cargo[move.from - 1].take(move.amount).toMutableList()
            cargo[move.to - 1].addAll(0, listToAppend)
            repeat(listToAppend.size){
                cargo[move.from - 1].removeAt(0)
            }
        }
        println(cargo)
        var returnStr = ""
        cargo.forEach {
            if(it.isNotEmpty()) {
                returnStr += it[0]
            }
        }
        return returnStr
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}
