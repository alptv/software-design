package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

class Num(val value : Int) : Token {
    override fun accept(visitor: Visitor<*>) {
        visitor.visit(this)
    }
}