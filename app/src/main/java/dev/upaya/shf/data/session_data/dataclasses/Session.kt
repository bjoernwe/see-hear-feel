package dev.upaya.shf.data.session_data.dataclasses

import java.time.OffsetDateTime


data class Session(
    val start: OffsetDateTime,
    val end: OffsetDateTime?,
)
