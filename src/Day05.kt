import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var lb = 0
        val ranges = mutableListOf<LongRange>()
        var res = 0
        input.forEach { line ->
            lb += if (line.isEmpty()) 1 else 0
            if (line.isEmpty()) return@forEach
            if (lb < 1) {
                val spl = line.split("-").map { it.toLong() }
                ranges.add(LongRange(spl[0], spl[1]))
            } else {
                val fresh = ranges.fold(false) { r, t -> r || line.toLong() in t }
                if (fresh) res++
            }
        }
        return res
    }

    fun part2(input: List<String>): Long {
        var lb = 0
        val ranges = mutableListOf<LongRange>()
        val merged = mutableListOf<LongRange>()
        input.forEach { line ->
            lb += if (line.isEmpty()) 1 else 0
            if (line.isEmpty()) return@forEach
            if (lb < 1) {
                val spl = line.split("-").map { it.toLong() }
                ranges.add(LongRange(spl[0], spl[1]))
            } else if (lb < 2) {
                ranges.sortBy { it.first }
                var current = ranges[0]
                for (i in 1 until ranges.size) {
                    if (ranges[i].first <= current.last) {
                        current = LongRange(current.first, max(current.last, ranges[i].last))
                    } else {
                        merged.add(current)
                        current = ranges[i]
                    }
                }
                merged.add(current)
                lb += 1
            }
        }
        return merged.sumOf { it.last - it.first + 1 }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 3)
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
