package dev.upaya.shf.data.sources

import java.util.Date


data class IntEvent(
    override val value: Int,
    override val date: Date = Date(),
) : Event<Int>
