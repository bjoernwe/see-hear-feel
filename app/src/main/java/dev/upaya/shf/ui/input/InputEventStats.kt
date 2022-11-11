package dev.upaya.shf.ui.input

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import dev.upaya.shf.exercises.labels.Label
import javax.inject.Inject


typealias LabelFreqs = Map<Label, Int>


class InputEventStats @Inject constructor() {

    private val _labelFreqs = mutableStateMapOf<Label, Int>()
    val labelFreqs: SnapshotStateMap<Label, Int> = _labelFreqs

    fun reportInputEvent(label: Label) {
        _labelFreqs[label] = labelFreqs.getOrDefault(label, defaultValue = 0) + 1
    }

}
