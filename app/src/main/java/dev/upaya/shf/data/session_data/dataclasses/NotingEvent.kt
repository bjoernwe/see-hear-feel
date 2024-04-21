package dev.upaya.shf.data.session_data.dataclasses

import dev.upaya.shf.data.labels.SHFLabel
import java.time.OffsetDateTime


data class NotingEvent(
    val date: OffsetDateTime? = OffsetDateTime.now(),
    val label: SHFLabel,
)
