package dev.upaya.shf.data.session_data.dataclasses

import java.time.Instant


data class Session(
    val start: Instant,
    val end: Instant?,
)
