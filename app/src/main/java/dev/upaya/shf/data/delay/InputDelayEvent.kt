package dev.upaya.shf.data.delay

import java.time.Instant


data class InputDelayEvent(
    val delaysInARow: Int,
    val delayInterval: Int,
    val timestamp: Instant = Instant.now(),
)
