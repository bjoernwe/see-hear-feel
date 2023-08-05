package dev.upaya.shf.inputs

import dev.upaya.shf.inputs.events.Event
import java.util.Date


data class IntEvent(
    override val value: Int,
    override val date: Date = Date(),
) : Event<Int>
