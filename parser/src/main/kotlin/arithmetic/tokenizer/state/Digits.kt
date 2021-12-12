package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.Token
import java.lang.StringBuilder

class Digits(
    tokenizer: Tokenizer,
) : PrimaryState(tokenizer), State {
    private val digits: StringBuilder = StringBuilder()

    constructor(tokenizer: Tokenizer, digit: Char) : this(tokenizer) {
        addDigit(digit)
    }

    override fun nextState(char: Char): Token? {
        return if (char.isDigit()) {
            addDigit(char)
            null
        } else {
            super.nextState(char)
        }
    }

    override fun createToken(): Token = Num(digits.toString().toInt())

    private fun addDigit(digit: Char) = digits.append(digit)
}