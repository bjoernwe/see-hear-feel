package dev.upaya.shf.feelings


class UrnList(initial: List<String>) {

    private val values = initial.shuffled().toMutableList()
    private var index = 0
    private var round = 0

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

    fun getRound(): Int {
        return round
    }

    private fun resetIndex() {
        if (index >= values.size) {
            index = 0
            round += 1
            values.shuffle()
        }
    }

}
