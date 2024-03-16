package dev.upaya.shf.data.session_history

import androidx.room.TypeConverter
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class Converters {

    @TypeConverter
    fun stringToInstant(value: String?): Instant? {
        return try {
            value?.let { Instant.parse(it) }
        } catch (e: DateTimeParseException) {
            null
        }
    }

    @TypeConverter
    fun instantToString(date: Instant?): String? {
        if (date == null) {
            return null
        }
        return DateTimeFormatter.ISO_INSTANT.format(date)
    }
}
