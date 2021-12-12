package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class CloseBracket : Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}