package dev.upaya.shf.data.delay

import java.util.Date


/**
 * "Event" here means "timestamped value"
 */
interface Event<T> {
    val value: T
    val date: Date
}
