package dev.upaya.shf.data.session_history

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class Converters {

    companion object {
        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    }

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

    @TypeConverter
    fun stringToDate(value: String?): LocalDate? {
        return try {
            value?.let { LocalDate.parse(value, dateFormatter) }
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
