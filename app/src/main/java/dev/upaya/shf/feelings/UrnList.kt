package dev.upaya.shf.feelings


class UrnList(initial: List<String>) {

    private val values = initial.shuffled().toMutableList()
    private var index = 0

    fun getCurrent(): String? {
        return values.getOrNull(index)
    }

    fun removeCurrent() {
        if (values.isEmpty()) { return }
        values.removeAt(index)
        resetIndex()
    }

    fun getNext(): String? {
        index += 1
        resetIndex()
        return getCurrent()
    }

    private fun resetIndex() {
        if (index >= values.size) {
            index = 0
            values.shuffle()
        }
    }

}