fun main() {
    fun flip(x: Char) = if (x == '.') '#' else '.'

    fun transform(initial: String, buttons: List<Int>): String {
        val arr = initial.toCharArray()
        buttons.forEach {
            arr[it] = flip(arr[it])
        }
        return arr.concatToString()
    }

    fun part1(input: List<String>): Int {
        var push = 0
        input.forEach { line ->
            val sp = line.split(" ")
            val desiredState = sp[0].substring(1 .. sp[0].length - 2)
            val initialState = MutableList(desiredState.length) { '.' }.joinToString("")
            val buttons = sp.filter { it[0] == '(' }.map {
                it.substring(1..it.length - 2).split(',').map { it.toInt() }
            }
            val queue = mutableListOf<Pair<String, Int>>()
            val visited = mutableSetOf<String>()
            queue.add(Pair(initialState, 0))
            visited.add(initialState)
            var found = false
            while (queue.isNotEmpty() && !found) {
                val curr = queue.removeFirst()
                buttons.forEach {
                    val newState = transform(curr.first, it)
                    if (newState == desiredState) {
                        push += curr.second + 1
                        found = true
                        return@forEach
                    }
                    if (newState !in visited) {
                        queue.add(Pair(newState, curr.second + 1))
                        visited.add(newState)
                    }
                }
            }
        }
        return push
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 7)
//    check(part2(testInput) == 222)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day10")
    part1(input).println()
//    part2(input).println()
}
