package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.source.Source
import arithmetic.tokenizer.token.Token

open class PrimaryState(
    val tokenizer: Tokenizer
) : State {

    override fun nextState(char: Char): Token? {
        tokenizer.state = when (char) {
            '(' -> LeftBracket(tokenizer)
            ')' -> RightBracket(tokenizer)
            '+' -> Plus(tokenizer)
            '-' -> Minus(tokenizer)
            '/' -> Slash(tokenizer)
            '*' -> Star(tokenizer)
            else -> when {
                char.isDigit() -> Digits(tokenizer, char)
                char.isWhitespace() -> PrimaryState(tokenizer)
                Source.isEOF(char) -> Eof()
                else -> Fault(char)
            }
        }
        return createToken()
    }

    override fun createToken(): Token? = null

}