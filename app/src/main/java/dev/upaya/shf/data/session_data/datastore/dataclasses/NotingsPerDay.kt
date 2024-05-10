package dev.upaya.shf.data.session_data.datastore.dataclasses

import java.time.LocalDate


data class NotingsPerDay(
    val count: Int,
    val day: LocalDate
)
