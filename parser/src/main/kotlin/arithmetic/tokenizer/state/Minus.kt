package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Subtract
import arithmetic.tokenizer.token.Token

class Minus(
    tokenizer: Tokenizer
) : PrimaryState(tokenizer), State {

    override fun createToken(): Token = Subtract()
}