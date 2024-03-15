package dev.upaya.shf.data.delay

import java.time.Instant


/**
 * "Event" here means "timestamped value"
 */
interface Event<T> {
    val value: T
    val date: Instant
}
