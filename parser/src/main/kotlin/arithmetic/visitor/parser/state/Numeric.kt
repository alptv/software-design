package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token
import java.lang.IllegalStateException

class Numeric : BaseState(), ValidationState  {
    override fun nextState(token: Token) : ValidationState {
        if (token is Num || token is OpenBracket) {
            throw IllegalStateException("Number or open bracket after number")
        }
        return super.nextState(token)
    }
}