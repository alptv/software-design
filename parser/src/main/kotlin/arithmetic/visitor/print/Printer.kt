package arithmetic.visitor.print

interface Printer {
    var separator : String
    fun print(input: String)
}