fun main() {
    fun part1(input: List<String>): Int {
        var cur = 50
        return input.count {
            val (direction, step) = Regex("([A-Za-z])(\\d+)")
                .matchEntire(it)!!
                .destructured
            val steps = step.toInt().mod(100)
            if (direction == "L") {
                cur -= steps
                if (cur < 0) cur += 100
            } else if (direction == "R") {
                cur += steps
                if (cur > 99) cur -= 100
            }
            cur == 0
        }
    }

    fun part2(input: List<String>): Int {
        var cur = 50
        var count = 0
        input.forEach {
            val (direction, step) = Regex("([A-Za-z])(\\d+)")
                .matchEntire(it)!!
                .destructured
            count += step.toInt().div(100)
            val steps = step.toInt().mod(100)
            val startAtZero = cur == 0
            if (direction == "L") {
                cur -= steps
                if (cur < 0) {
                    cur += 100
                    val endAtZero = cur == 0
                    if (!endAtZero && !startAtZero) count += 1
                }
            } else if (direction == "R") {
                cur += steps
                if (cur > 99) {
                    cur -= 100
                    val endAtZero = cur == 0
                    if (!endAtZero && !startAtZero) count += 1
                }
            }
            if (cur == 0) count += 1
        }
        return count
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
