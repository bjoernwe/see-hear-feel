package dev.upaya.shf.data.session_data.dataclasses

import dev.upaya.shf.data.delay.InputDelayEvent


data class SessionWithEvents(
    val session: Session,
    val notings: List<NotingEvent>,
    val delays: List<InputDelayEvent>,
)
