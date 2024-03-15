package dev.upaya.shf.data.gamepad_input

import java.time.Instant

data class SHFLabelEvent(
    val label: SHFLabel,
    val timestamp: Instant = Instant.now(),
)
