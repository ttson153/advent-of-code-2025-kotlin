import kotlin.math.abs

data class Point(
    val x: Int,
    val y: Int
)

fun main() {
    fun area(p1: Point, p2: Point): Long = abs(p1.x - p2.x + 1).toLong() * abs(p1.y - p2.y + 1)

    fun part1(input: List<String>): Long {
        val points = input.map {
            val (x, y) = it.split(",")
            Point(x.toInt(), y.toInt())
        }
        var maxArea = 0L
        for (i in 0 until points.size) {
            for (j in i until points.size) {
                if (area(points[i], points[j]) > maxArea) {
                    maxArea = area(points[i], points[j])
                }
            }
        }
        return maxArea
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 50L)
//    check(part2(testInput) == 222)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day09")
    part1(input).println()
//    part2(input).println()
}
