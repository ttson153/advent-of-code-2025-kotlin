fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val digits = it.map { it.digitToInt() }
            var max = 0
            for (i in 0 until digits.size - 1) {
                for (j in i + 1 until digits.size) {
                    val ij = "${digits[i]}${digits[j]}".toInt()
                    if (max < ij) max = ij
                }
            }
            max
        }
    }

    fun part2(input: List<String>): Long {
        return input.sumOf {
            var toDelete = it.length - 12
            val digits = it.map { it.digitToInt() }
            val stack = mutableListOf<Int>()
            digits.forEach {
                while (stack.isNotEmpty() && toDelete > 0 && stack.last() < it) {
                    stack.removeLast()
                    toDelete--
                }
                stack.addLast(it)
            }
            stack.take(12).joinToString(separator = "") { "$it" }.toLong()
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 357)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
