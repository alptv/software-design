package arithmetic

import arithmetic.tokenizer.Tokenizer
import arithmetic.tokenizer.source.Source
import arithmetic.tokenizer.source.StringSource
import arithmetic.visitor.eval.EvalVisitor
import arithmetic.visitor.parser.ParserVisitor
import arithmetic.visitor.print.PrintVisitor
import arithmetic.visitor.print.StdoutPrinter

fun main() {
    try {
        println("Write expression on one line:\n")
        val expression: String = readLine()!!
        val source: Source = StringSource(expression)
        val tokens = Tokenizer(source).tokenize()

        val parserVisitor = ParserVisitor()
        tokens.forEach { it.accept(parserVisitor) }
        val reversedPolishNotation = parserVisitor.getReversedPolishNotation()

        val printVisitor = PrintVisitor(StdoutPrinter())
        print("Reversed polish notation: ")
        reversedPolishNotation.forEach { it.accept(printVisitor) }
        println()

        val evalVisitor = EvalVisitor()
        reversedPolishNotation.forEach { it.accept(evalVisitor) }
        println("Result: ${evalVisitor.evalValue()}")

    } catch (e: Exception) {
        println(e.message)
    }
}