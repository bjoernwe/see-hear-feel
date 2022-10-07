package dev.upaya.shf.inputs

import java.util.Date


data class InputEvent(
    val inputKey: InputKey,
    val date: Date = Date(),
)
