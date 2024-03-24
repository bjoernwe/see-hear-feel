package dev.upaya.shf.data.labels

import java.time.OffsetDateTime


data class SHFLabelEvent(
    val label: SHFLabel,
    val timestamp: OffsetDateTime = OffsetDateTime.now(),
)
