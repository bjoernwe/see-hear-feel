package dev.upaya.shf.data.sources

import java.util.Date


data class InputEvent(
    val inputKey: InputKey,
    val date: Date = Date(),
)
