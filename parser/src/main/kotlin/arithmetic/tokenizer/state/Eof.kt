package arithmetic.tokenizer.state

import arithmetic.tokenizer.token.Token

class Eof : State {

    override fun nextState(char: Char): Token? = null

    override fun createToken(): Token? = null
}