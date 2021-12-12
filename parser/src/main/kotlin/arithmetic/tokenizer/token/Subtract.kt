package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class Subtract : Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}