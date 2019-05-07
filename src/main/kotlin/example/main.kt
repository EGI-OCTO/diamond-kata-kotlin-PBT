package example

fun buildDiamond(letter: Char) : Array<String> {
    val letters = 'A'..letter
    val size = letters.count()
    return letters.mapIndexed { index, c ->
        val spacesBefore = CharArray(size - index - 1) { ' ' }
        "${spacesBefore.joinToString("")}$c"
    }.toTypedArray()
}

fun main() {
    buildDiamond('D').forEach { println(it) }
}
