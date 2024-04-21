package dev.upaya.shf.data.session_data.dataclasses

import dev.upaya.shf.data.delay.InputDelayEvent
import dev.upaya.shf.data.labels.SHFLabelEvent


data class SessionWithEvents(
    val session: Session,
    val notings: List<SHFLabelEvent>,
    val delays: List<InputDelayEvent>,
)
