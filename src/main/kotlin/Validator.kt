import java.util.stream.IntStream

class Validator(var transitions: Map<Int, Map<Set<Char>, Int>>, var finalState: Int) {

    fun isValid(text: String): Boolean {
        var state = 1

        for (c in text) {
            val transition = transitions[state] ?: return false
            val next = transition.keys.firstOrNull { c in it } ?: return false
            state = transition[next] ?: return false
        }

        return state == finalState
    }

    fun getValidFromText(text: String): List<String> {
        val emails = mutableListOf<String>()

        IntStream.range(0, text.length).forEach { i ->
            IntStream.range(i, text.length).forEach { j ->
                val substring = text.substring(i, j + 1)
                if (isValid(substring)) {
                    emails.add(substring)
                }
            }
        }

        return emails
    }
}