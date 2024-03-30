package dev.upaya.shf.data.session_history

import java.time.OffsetDateTime


data class Session(
    val id: Long,
    val start: OffsetDateTime,
    val end: OffsetDateTime?,
)
