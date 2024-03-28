package dev.upaya.shf.data.session_history.dataclasses

import java.time.OffsetDateTime


data class Session(
    val id: Int = 0,
    val start: OffsetDateTime,
    var end: OffsetDateTime? = null,
)
