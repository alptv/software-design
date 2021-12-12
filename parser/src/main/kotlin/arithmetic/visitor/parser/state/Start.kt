package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token
import java.lang.IllegalStateException


class Start : BaseState(), ValidationState {
    override fun nextState(token: Token): ValidationState {
        if (token !is Num && token !is OpenBracket) {
            throw IllegalStateException("Expression can start only with number or open bracket")
        }
        return super.nextState(token)
    }
}
