package arithmetic.visitor.parser

import arithmetic.tokenizer.token.*
import arithmetic.visitor.Visitor
import arithmetic.visitor.parser.state.CloseBrace
import arithmetic.visitor.parser.state.Numeric
import arithmetic.visitor.parser.state.Start
import arithmetic.visitor.parser.state.ValidationState
import java.util.*

class ParserVisitor : Visitor {
    private val operations = Stack<Token>()
    private val reversedPolishNotation = mutableListOf<Token>()
    private var validationState: ValidationState = Start()

    override fun visit(openBracket: OpenBracket) {
        nextState(openBracket)
        operations.push(openBracket)
    }

    override fun visit(closeBracket: CloseBracket) {
        nextState(closeBracket)
        while (!operations.empty() && operations.peek() !is OpenBracket) {
            reversedPolishNotation.add(operations.pop())
        }
        if (operations.empty()) {
            throw IllegalStateException("Mismatched open bracket")
        }
        operations.pop()
    }

    override fun visit(number: Num) {
        nextState(number)
        reversedPolishNotation.add(number)
    }

    override fun visit(sum: Sum) {
        visitOperation(sum)
    }

    override fun visit(subtract: Subtract) {
        visitOperation(subtract)
    }

    override fun visit(multiply: Multiply) {
        visitOperation(multiply)
    }

    override fun visit(divide: Divide) {
        visitOperation(divide)
    }

    fun getReversedPolishNotation(): List<Token> {
        if (validationState !is Numeric && validationState !is CloseBrace) {
            throw IllegalStateException("Expression should ends with number or close bracket")
        }
        if (operations.firstOrNull { it is OpenBracket } != null) {
            throw IllegalStateException("Mismatched close bracket")
        }
        return reversedPolishNotation + operations.reversed()
    }

    private fun visitOperation(operation: Token) {
        nextState(operation)
        while (!operations.empty() && operations.peek() !is OpenBracket) {
            val previousOperation = operations.peek()
            if (previousOperation.getPriority() >= operation.getPriority()) {
                reversedPolishNotation.add(previousOperation)
                operations.pop()
            } else {
                break
            }
        }
        operations.push(operation)
    }

    private fun Token.getPriority() = when (this) {
        is Divide, is Multiply -> 2
        is Subtract, is Sum -> 1
        else -> throw IllegalArgumentException("Non operation can't have priority")
    }

    private fun nextState(token: Token) {
        validationState = validationState.nextState(token)
    }
}
