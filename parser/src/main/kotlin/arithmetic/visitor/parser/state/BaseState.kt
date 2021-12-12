package arithmetic.visitor.parser.state

import arithmetic.tokenizer.token.CloseBracket
import arithmetic.tokenizer.token.Num
import arithmetic.tokenizer.token.OpenBracket
import arithmetic.tokenizer.token.Token

abstract class BaseState : ValidationState {

    override fun nextState(token: Token): ValidationState = when (token) {
        is CloseBracket -> CloseBrace()
        is OpenBracket -> OpenBrace()
        is Num -> Numeric()
        else -> Operation()
    }
}