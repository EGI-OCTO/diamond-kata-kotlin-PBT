package example

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec
import java.lang.Math.floor

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
                .map { diamond[it.first][it.second] }
            lettersInUpperLeftDiagonal == (letter downTo 'A').toList()
        }
    }
    "A diamond has decreasing letter sequence in its upper right quadrant" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            val lettersInUpperRightDiagonal = buildUpperRightDiagonalCoordinates(letter)
                .map { diamond[it.first][it.second] }
            lettersInUpperRightDiagonal == (letter downTo 'A').toList()
        }
    }
    "A diamond should be symmetrical horizontally" {
        forAll(LetterGenerator()) { letter: Char ->
            val diamond = buildDiamond(letter)
            isSymmetricalHorizontally(diamond)
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
    val lastLetterIndex = ('A'..letter).count() - 1
    val yCoordinates = lastLetterIndex downTo 0
    val xCoordinates = 0..lastLetterIndex
    return yCoordinates.mapIndexed { index, i -> i to xCoordinates.elementAt(index) }
}

fun buildUpperRightDiagonalCoordinates(letter: Char): List<Pair<Int, Int>> {
    val lastLetterIndex = ('A'..letter).count() - 1
    val yCoordinates = lastLetterIndex downTo 0
    val xCoordinates = (lastLetterIndex * 2) downTo lastLetterIndex
    return yCoordinates.mapIndexed { index, i -> i to xCoordinates.elementAt(index) }
}

fun isSymmetricalHorizontally(diamond: Array<String>): Boolean {
    val half = floor(diamond.size / 2.toDouble()).toInt()
    val top = diamond.slice(0..half)
    val bottom = diamond.slice(half until diamond.size)
    return top == bottom.reversed()
}
