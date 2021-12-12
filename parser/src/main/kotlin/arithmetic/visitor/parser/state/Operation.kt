package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token
import java.lang.IllegalStateException

class Operation : BaseState(), ValidationState {
    override fun nextState(token: Token): ValidationState {
        if (token !is Num && token !is OpenBracket) {
            throw IllegalStateException("Not number or open bracket after operation")
        }
        return super.nextState(token)
    }
}