package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class OpenBracket: Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}