package dev.upaya.shf.data.session_history

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.Instant
import java.time.LocalDate


class ConvertersTest {

    private val instantString = "2000-12-31T23:59:59Z"
    private val instant = Instant.parse("2000-12-31T23:59:59Z")

    private val dateString = "2000-12-31"
    private val date = LocalDate.of(2000, 12, 31)

    private var testUnit = Converters()

    @Test
    fun stringToInstant_nullDate_returnsNull() {

        // GIVEN a date converter
        // WHEN null is parsed
        val parsedDate = testUnit.stringToInstant(null)

        // THEN the result is null
        assertNull(parsedDate)
    }

    @Test
    fun stringToInstant_invalidDate_returnsNull() {

        // GIVEN a date converter
        // WHEN an invalid date is parsed
        val parsedDate = testUnit.stringToInstant("INVALID DATE")

        // THEN the result is null
        assertNull(parsedDate)
    }

    @Test
    fun stringToInstant_date_returnsDate() {

        // GIVEN a date converter
        // WHEN a date string is parsed
        val parsedDate = testUnit.stringToInstant(instantString)

        // THEN the result is correct
        assertEquals(instant, parsedDate)
    }

    @Test
    fun instantToString_null_returnsNull() {

        // GIVEN a date converter
        // WHEN null is formatted
        val formattedDate = testUnit.instantToString(null)

        // THEN it is null
        assertNull(formattedDate)
    }

    @Test
    fun instantToString_instant_returnsCorrectString() {

        // GIVEN a date converter
        // WHEN an Instant is formatted
        val formattedDate = testUnit.instantToString(instant)

        // THEN the result is correct
        assertEquals(instantString, formattedDate)
    }

    @Test
    fun stringToDate_localDate_returnsDate() {

        // GIVEN a date converter
        // WHEN a date string is parsed
        val parsedDate = testUnit.stringToDate(dateString)

        // THEN the result is correct
        assertEquals(date, parsedDate)
    }
}
