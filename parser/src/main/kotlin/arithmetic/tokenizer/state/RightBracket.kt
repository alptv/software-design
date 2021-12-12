package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.CloseBracket
import arithmetic.tokenizer.token.Token

class RightBracket(
    tokenizer: Tokenizer
) : PrimaryState(tokenizer), State {

    override fun createToken(): Token = CloseBracket()
}