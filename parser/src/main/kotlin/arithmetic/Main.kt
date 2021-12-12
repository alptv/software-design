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
        val reversedPolishNotation = parserVisitor.visit(tokens)
        val printVisitor = PrintVisitor(StdoutPrinter())
        print("Reversed polish notation: ")
        printVisitor.visit(reversedPolishNotation)
        println()
        val evalVisitor = EvalVisitor()
        println("Result: ${evalVisitor.visit(reversedPolishNotation)}")

    } catch (e: Exception) {
        print(e.message)
    }
}