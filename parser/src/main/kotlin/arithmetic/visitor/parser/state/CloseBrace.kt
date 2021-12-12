package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token
import java.lang.IllegalStateException

class CloseBrace : BaseState(), ValidationState {
    override fun nextState(token: Token): ValidationState {
        if (token is Num || token is OpenBracket) {
            throw IllegalStateException("Non operation after close bracket")
        } else {
            return super.nextState(token)
        }
    }

}