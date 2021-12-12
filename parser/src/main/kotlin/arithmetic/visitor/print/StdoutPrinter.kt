package arithmetic.visitor.print

class StdoutPrinter : Printer {
    override var separator = ""

    override fun print(input: String) {
        kotlin.io.print(input + separator)
    }
}