fun main() {
    fun part1(input: List<String>): Int {
        val n = input.size
        val m = input[0].length
        val dx = listOf(-1, -1, -1, 0, 1, 1, 1, 0)
        val dy = listOf(-1, 0, 1, 1, 1, 0, -1, -1)
        var res = 0
        for (i in 0 until n) {
            for (j in 0 until m) {
                var sum = 0
                for (k in dx.indices) {
                    val ki = i + dx[k]
                    val kj = j + dy[k]
                    if (ki in 0 until n && kj in 0 until m && input[kj][ki] == '@') sum++
                }
                if (input[j][i] == '@' && sum < 4) res++
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toCharArray() }.toMutableList()
        val n = grid.size
        val m = grid[0].size
        val dx = listOf(-1, -1, -1, 0, 1, 1, 1, 0)
        val dy = listOf(-1, 0, 1, 1, 1, 0, -1, -1)
        var res = 0

        do {
            val removedX = mutableListOf<Int>()
            val removedY = mutableListOf<Int>()
            var found = false
            for (i in 0 until n) {
                for (j in 0 until m) {
                    var sum = 0
                    for (k in dx.indices) {
                        val ki = i + dx[k]
                        val kj = j + dy[k]
                        if (ki in 0 until n && kj in 0 until m && grid[kj][ki] == '@') sum++
                    }
                    if (grid[j][i] == '@' && sum < 4) {
                        removedX.add(i)
                        removedY.add(j)
                        res++
                        found = true
                    }
                }
            }
            for (k in removedX.indices) {
                grid[removedY[k]][removedX[k]] = '.'
            }
        } while (found)
        return res
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 43)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
