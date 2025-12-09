import kotlin.math.sqrt

data class Point3D(
    val x: Int,
    val y: Int,
    val z: Int
)

fun main() {
    fun distance(p1: Point3D, p2: Point3D): Double {
        val dx = (p1.x - p2.x).toDouble()
        val dy = (p1.y - p2.y).toDouble()
        val dz = (p1.z - p2.z).toDouble()
        return sqrt(dx * dx + dy * dy + dz * dz)
    }

    fun <T> sumSet(s1: Set<T>?, s2: Set<T>?): Set<T> {
        val r = mutableSetOf<T>()
        s1?.let { r.addAll(it) }
        s2?.let { r.addAll(it) }
        return r
    }

    fun part1(input: List<String>): Int {
        val n = input.size
        val points = input.map {
            val (x, y, z) = it.split(",").map { it.toInt() }
            Point3D(x, y, z)
        }
        val dist = MutableList(n) { MutableList(n) { 0.0 } }
        for (i in 0 until n) {
            for (j in i until n) {
                val d = distance(points[i], points[j])
                dist[i][j] = d
                dist[j][i] = d
            }
            dist[i][i] = Double.MAX_VALUE
        }
        val section = (0 until n).map { setOf(it) }.toMutableSet()
        var pass = 1000
        while (pass > 0) {
            var mi = Double.MAX_VALUE
            var mx = -1
            var my = -1
            for (i in 0 until n) {
                for (j in i until n) {
                    if (dist[i][j] < mi) {
                        mi = dist[i][j]
                        mx = i
                        my = j
                    }
                }
            }
            val combined = sumSet(section.find { it.contains(mx) }, section.find { it.contains(my) })
            section.removeAll { it.contains(mx) || it.contains(my) }
            section.add(combined)
            dist[mx][my] = Double.MAX_VALUE
            dist[my][mx] = Double.MAX_VALUE
            pass--
        }
        return section.sortedByDescending { it.size }.take(3).fold(1) { a, c -> a * c.size}
    }

    fun part2(input: List<String>): Long {
        val n = input.size
        val points = input.map {
            val (x, y, z) = it.split(",").map { it.toInt() }
            Point3D(x, y, z)
        }
        val dist = MutableList(n) { MutableList(n) { 0.0 } }
        for (i in 0 until n) {
            for (j in i until n) {
                val d = distance(points[i], points[j])
                dist[i][j] = d
                dist[j][i] = d
            }
            dist[i][i] = Double.MAX_VALUE
        }
        val section = (0 until n).map { setOf(it) }.toMutableSet()
        while (true) {
            var mi = Double.MAX_VALUE
            var mx = -1
            var my = -1
            for (i in 0 until n) {
                for (j in i until n) {
                    if (dist[i][j] < mi) {
                        mi = dist[i][j]
                        mx = i
                        my = j
                    }
                }
            }
            val combined = sumSet(section.find { it.contains(mx) }, section.find { it.contains(my) })
            section.removeAll { it.contains(mx) || it.contains(my) }
            section.add(combined)
            dist[mx][my] = Double.MAX_VALUE
            dist[my][mx] = Double.MAX_VALUE
            if (section.size == 1) {
                return points[mx].x.toLong() * points[my].x
            }
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day08_test")
//    check(part1(testInput) == 40)
    check(part2(testInput) == 25272L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}
