package arithmetic.visitor

import arithmetic.tokenizer.token.*

interface Visitor {

    fun visit(openBracket: OpenBracket)

    fun visit(closeBracket: CloseBracket)

    fun visit(number: Num)

    fun visit(sum: Sum)

    fun visit(subtract: Subtract)

    fun visit(multiply: Multiply)

    fun visit(divide: Divide)


}