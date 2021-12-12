package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class Sum : Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}