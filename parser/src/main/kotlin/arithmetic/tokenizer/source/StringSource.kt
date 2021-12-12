package arithmetic.tokenizer.source

class StringSource(private val source: String) : Source {
    private var index = 0

    override fun nextChar(): Char {
        return if (index < source.length) {
            source[index++];
        } else {
            Source.EOF
        }
    }
}