package arithmetic.tokenizer.token

import arithmetic.visitor.Visitor

interface Token {
    fun accept(visitor: Visitor)
}