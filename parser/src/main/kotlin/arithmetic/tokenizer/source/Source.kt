package arithmetic.tokenizer.source

interface Source {
    companion object {
        const val EOF = 0.toChar()
        fun isEOF(char: Char) = char == EOF
    }
    fun nextChar() : Char

}