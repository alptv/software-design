package arithmetic.visitor.print

import arithmetic.tokenizer.token.*
import arithmetic.visitor.Visitor

class PrintVisitor(
    private val printer: Printer
) : Visitor {
    init {
        printer.separator = " "
    }


    override fun visit(openBracket: OpenBracket) {
        printer.print("(")
    }

    override fun visit(closeBracket: CloseBracket) {
        printer.print(")")
    }

    override fun visit(number: Num) {
        printer.print(number.value.toString())
    }

    override fun visit(sum: Sum) {
        printer.print("+")
    }

    override fun visit(subtract: Subtract) {
        printer.print("-")
    }

    override fun visit(multiply: Multiply) {
        printer.print("*")
    }

    override fun visit(divide: Divide) {
        printer.print("/")
    }
}