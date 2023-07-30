package dev.upaya.shf.inputs.events

import dev.upaya.shf.inputs.keys.InputKey
import java.util.Date


data class InputEvent(
    val inputKey: InputKey,
    val date: Date = Date(),
)
