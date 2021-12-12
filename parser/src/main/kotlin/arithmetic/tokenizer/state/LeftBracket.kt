package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token

class LeftBracket(
    tokenizer: Tokenizer
) : PrimaryState(tokenizer), State {

    override fun createToken(): Token = OpenBracket()

}