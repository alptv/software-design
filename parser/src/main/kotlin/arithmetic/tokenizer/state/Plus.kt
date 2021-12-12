package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.Subtract
import arithmetic.tokenizer.token.Sum
import arithmetic.tokenizer.token.Token

class Plus(tokenizer: Tokenizer) : PrimaryState(tokenizer), State {

    override fun createToken(): Token = Sum()
}