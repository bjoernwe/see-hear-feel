package dev.upaya.shf.data.delay

import java.time.Instant


data class DelayedInputEvent(
    val delaysInARow: Int,
    val timestamp: Instant = Instant.now(),
)
