package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.Multiply
import arithmetic.tokenizer.token.Token

class Star(tokenizer: Tokenizer) : PrimaryState(tokenizer), State {
    override fun createToken(): Token = Multiply()
}