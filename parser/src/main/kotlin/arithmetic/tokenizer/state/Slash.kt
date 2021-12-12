package arithmetic.tokenizer.state

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.token.Divide
import arithmetic.tokenizer.token.Token

class Slash(tokenizer: Tokenizer) : PrimaryState(tokenizer), State {

    override fun createToken(): Token = Divide()
}