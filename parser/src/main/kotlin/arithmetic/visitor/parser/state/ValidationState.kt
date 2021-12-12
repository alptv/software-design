package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Token

interface ValidationState {

    fun nextState(token: Token) : ValidationState
}