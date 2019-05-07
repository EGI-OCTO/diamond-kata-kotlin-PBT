package example

import io.kotlintest.properties.Gen
import io.kotlintest.properties.forAll
import io.kotlintest.specs.StringSpec

class MyTests : StringSpec({
    "Diamond should never be empty" {
        forAll(LetterGenerator()) {
            a : Char -> buildDiamond(a).isNotEmpty()
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