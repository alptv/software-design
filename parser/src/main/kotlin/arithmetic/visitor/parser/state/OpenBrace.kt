package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token

class OpenBrace : BaseState(), ValidationState {

    override fun nextState(token: Token): ValidationState {
        if (token !is Num && token !is OpenBracket) {
            throw IllegalStateException("After open bracket can be only number")
        }
        return super.nextState(token)
    }

}