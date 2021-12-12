package arithmetic.tokenizer.state

import arithmetic.tokenizer.token.Token

interface State {

    fun nextState(char: Char): Token?

    fun createToken(): Token?

}