package arithmetic.tokenizer

import arithmetic.tokenizer.source.Source
import arithmetic.tokenizer.state.Eof
import arithmetic.tokenizer.state.Fault
import arithmetic.tokenizer.state.PrimaryState
import arithmetic.tokenizer.state.State
import arithmetic.tokenizer.token.Token

class Tokenizer(private val source: Source) {
    var state: State = PrimaryState(this)

    fun tokenize(): List<Token> {
        val tokens = mutableListOf<Token>()
        while (state !is Eof) {
            val token = state.nextState(source.nextChar())

            if (state is Fault) {
                throw IllegalStateException("Unexpected character : '${(state as Fault).char}'")
            }

            if (token != null) {
                tokens.add(token)
            }
        }
        return tokens
    }
}