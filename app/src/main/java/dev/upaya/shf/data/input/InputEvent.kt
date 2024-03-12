package dev.upaya.shf.data.input

import java.util.Date


data class InputEvent(
    val inputKey: InputKey,
    val date: Date = Date(),    // TODO: Use Instant instead?
)
