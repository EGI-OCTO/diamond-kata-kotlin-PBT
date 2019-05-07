package example

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class MyTests : StringSpec({
    "Diamond should never be empty" {
        forAll(LetterGenerator()) { a: Char ->
            buildDiamond(a).isNotEmpty()
        }
    }
    "A diamond has decreasing letter sequence in its upper left quadrant" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            val lettersInUpperLeftDiagonal = buildUpperLeftDiagonalCoordinates(letter)
                    .map { diamond[it.second][it.first] }
            lettersInUpperLeftDiagonal == (letter downTo 'A').toList()
        }
    }
})

class LetterGenerator : Gen<Char> {
    private val letters = 'A'..'Z'
    override fun constants() = emptyList<Char>()
    override fun random() = generateSequence {
        letters.random()
    }
}

fun buildUpperLeftDiagonalCoordinates(letter: Char): List<Pair<Int, Int>> {
    val length = ('A'..letter).count() - 1
    val xCoordinates = 0..length
    val yCoordinates = length downTo 0
    return xCoordinates.mapIndexed { index, i -> i to yCoordinates.elementAt(index) }
}
