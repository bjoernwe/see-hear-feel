package dev.upaya.shf.data.delay

import java.time.Instant


data class IntEvent(
    val value: Int,
    val timestamp: Instant = Instant.now(),
)
