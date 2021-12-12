package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class Multiply : Token {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}