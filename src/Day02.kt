fun String.isRepeater(): Boolean {
    if (length.mod(2) != 0) return false
    return substring(0, length / 2) == substring(length / 2, length)
}

fun String.isRepeater2(): Boolean = Regex("""^(\d+)\1+$""").matches(this)

fun main() {
    fun part1(input: List<String>): Long {
        var res = 0L
        input[0].split(',').forEach { range ->
            val (start, end) = range.split('-')
            res += (start.toLong()..end.toLong()).sumOf {
                if (it.toString().isRepeater()) it else 0
            }
        }
        return res
    }

    fun part2(input: List<String>): Long {
        var res = 0L
        input[0].split(',').forEach { range ->
            val (start, end) = range.split('-')
            res += (start.toLong()..end.toLong()).sumOf {
                if (it.toString().isRepeater2()) it else 0
            }
        }
        return res
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 1227775554L)
    check(part2(testInput) == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
