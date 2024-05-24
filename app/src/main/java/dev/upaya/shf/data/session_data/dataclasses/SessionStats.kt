package dev.upaya.shf.data.session_data.dataclasses


data class SessionStats (
    val numberOfNotings: Int?,
    val sessionDurationSeconds: Long?,
    val amountMindWandering: Float,
    val showMindWandering: Boolean,
)
