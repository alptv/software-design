package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class Divide : Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}