package dev.upaya.shf.inputs.events

import java.util.Date


/**
 * "Event" here means "timestamped value"
 */
interface Event<T> {
    val value: T
    val date: Date
}
