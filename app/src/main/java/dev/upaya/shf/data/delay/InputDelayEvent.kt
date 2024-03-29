package dev.upaya.shf.data.delay

import java.time.OffsetDateTime


data class InputDelayEvent(
    val delaysInARow: Int,
    val delayInterval: Int,
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
)
