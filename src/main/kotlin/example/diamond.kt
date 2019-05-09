package example

fun buildDiamond(untilLetter: Char): Array<String> {
    val buildLineForLetter: (Char) -> String = buildLineForLetter(untilLetter)
    val buildDiamondFrom: (Char) -> Array<String> = buildDiamondFrom(untilLetter, buildLineForLetter)
    return buildDiamondFrom('A')
}

private fun buildDiamondFrom(
    finalLetter: Char,
    buildLineForLetter: (Char) -> String
): (Char) -> Array<String> {
    return fun(letter: Char): Array<String> {
        return if (letter == finalLetter) {
            arrayOf(buildLineForLetter(letter))
        } else {
            val outerLine = buildLineForLetter(letter)
            val innerLines = buildDiamondFrom(finalLetter, buildLineForLetter)(letter.inc())
            arrayOf(outerLine)
                .plus(innerLines)
                .plus(outerLine)
        }
    }
}

private fun buildLineForLetter(
    finalLetter: Char
): (Char) -> String {
    val letters = 'A'..finalLetter
    val size = letters.count()
    return fun(letter: Char): String {
        val index = letters.indexOf(letter)
        return if (letter == 'A') {
            val spacesBefore = blankString(size - index - 1)
            "$spacesBefore$letter"
        } else {
            val spacesBefore = blankString(size - index - 1)
            val spacesInBetween = blankString((index * 2) - 1)
            "$spacesBefore$letter$spacesInBetween$letter"
        }
    }
}

private fun blankString(length: Int) =
    CharArray(length) { ' ' }.joinToString("")