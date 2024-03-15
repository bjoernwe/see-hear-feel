package dev.upaya.shf.data.labels

import java.time.Instant

data class SHFLabelEvent(
    val label: SHFLabel,
    val timestamp: Instant = Instant.now(),
)
