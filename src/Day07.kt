fun main() {
    fun part1(input: List<String>): Int {
        val grid = input.map { it.toCharArray() }.toMutableList()
        val j = input[0].indexOf("S")
        grid[1][j] = '|'
        var res = 0
        for (i in 2 until grid.size) {
            for (j in 0 until grid[0].size) {
                if (grid[i - 1][j] == '|') {
                    if (grid[i][j] == '^') {
                        if (j - 1 in 0 until grid[0].size) grid[i][j - 1] = '|'
                        if (j + 1 in 0 until grid[0].size) grid[i][j + 1] = '|'
                        res += 1
                    } else {
                        grid[i][j] = '|'
                    }
                }
            }
        }
        return res
    }

    fun part2(input: List<String>): Long {
        val grid = input.map { it.toCharArray() }.toMutableList()
        val k = input[0].indexOf("S")
        grid[1][k] = '|'
        var res = 0
        for (i in 2 until grid.size) {
            for (j in 0 until grid[0].size) {
                if (grid[i - 1][j] == '|') {
                    if (grid[i][j] == '^') {
                        if (j - 1 in 0 until grid[0].size) grid[i][j - 1] = '|'
                        if (j + 1 in 0 until grid[0].size) grid[i][j + 1] = '|'
                        res += 1
                    } else {
                        grid[i][j] = '|'
                    }
                }
            }
        }
        val dp = (0L until grid.size).map { (0L until grid[0].size).toMutableList() }.toMutableList()
        dp.last().indices.forEach { j -> dp.last()[j] = if (grid.last()[j] == '.') 0 else 1 }
        for (i in dp.size - 2 downTo 0) {
            for (j in 0 until dp[0].size) {
                dp[i][j] = when (grid[i][j]) {
                    '.' -> 0
                    '|' -> dp[i + 1][j]
                    else -> if (j in 1 .. dp[0].size - 2) {
                        dp[i + 1][j - 1] + dp[i + 1][j + 1]
                    } else if (j > 0) {
                        dp[i + 1][j - 1]
                    } else {
                        dp[i + 1][j + 1]
                    }
                }
            }
        }
        return dp[1][k]
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 40L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
