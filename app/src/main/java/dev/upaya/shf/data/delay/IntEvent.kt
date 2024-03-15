package dev.upaya.shf.data.delay

import java.time.Instant


data class IntEvent(
    override val value: Int,
    override val date: Instant = Instant.now(),
) : Event<Int>
