package arithmetic.visitor.eval

import arithmetic.tokenizer.token.*
import arithmetic.visitor.Visitor
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.util.*

class EvalVisitor : Visitor {
    private val stack = Stack<Int>()

    override fun visit(openBracket: OpenBracket) {
        throw IllegalStateException("Bracket can't be in reversed polish notation")
    }

    override fun visit(closeBracket: CloseBracket) {
        throw IllegalStateException("Bracket can't be in reversed polish notation")
    }

    override fun visit(number: Num) {
        stack.push(number.value)
    }

    override fun visit(sum: Sum) {
        visitOperation(Int::plus)
    }

    override fun visit(subtract: Subtract) {
        visitOperation(Int::minus)
    }

    override fun visit(multiply: Multiply) {
        visitOperation(Int::times)
    }

    override fun visit(divide: Divide) {
        visitOperation { divisible, divisor ->
            if (divisor == 0) {
                throw IllegalArgumentException("Division by zero")
            }
            divisible / divisor
        }
    }

    fun evalValue(): Int {
        if (stack.size != 1) {
            throw IllegalStateException("Incorrect reversed notation")
        }
        return stack.peek()

    }

    private fun visitOperation(operation: (Int, Int) -> Int) {
        if (stack.size < 2) {
            throw IllegalStateException("Incorrect reversed notation")
        }
        val secondArgument = stack.pop()
        val firstArgument = stack.pop()
        stack.push(operation(firstArgument, secondArgument))
    }

}