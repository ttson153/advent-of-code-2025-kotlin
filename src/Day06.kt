fun main() {
    fun part1(input: List<String>): Long {
        val nums = mutableListOf<List<Long>>()
        var res = 0L
        input.forEach { line ->
            val arr = line.trim().split("\\s+".toRegex())
            if (arr[0] in setOf("+", "*")) {
                arr.forEachIndexed { i, v ->
                    res += if (v == "+") {
                        nums.fold(0L) { a, c -> a + c[i] }
                    } else {
                        nums.fold(1L) { a, c -> a * c[i] }
                    }
                }
            } else {
                nums.add(arr.map { it.toLong() })
            }
        }
        return res
    }

    fun part2(input: List<String>): Long {
        val ops = input.last().trim().split("\\s+".toRegex()).reversed()
        val nums = mutableListOf<String>()
        val l = input.maxOf { it.length }
        for (i in l - 1 downTo 0) {
            var acc = ""
            for (j in 0 until input.size - 1) {
                acc += if (i < input[j].length) input[j][i] else ""
            }
            nums.add(acc)
        }
        var i = 0
        var res = 0L
        var acc = 0L
        if (ops[i] == "*") acc = 1L
        for (num in nums) {
            if (num.isBlank()) {
                i++
                res += acc
                acc = 0
                if (ops[i] == "*") acc = 1L
            } else {
                if (ops[i] == "+") {
                    acc += num.trim().toLong()
                } else {
                    acc *= num.trim().toLong()
                }
            }
        }
        res += acc
        return res
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
