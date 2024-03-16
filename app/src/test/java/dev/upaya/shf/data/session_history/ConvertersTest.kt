package dev.upaya.shf.data.session_history

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import java.time.Instant


class ConvertersTest {

    private val dateString = "2000-12-31T23:59:59Z"
    private val dateInstant = Instant.parse("2000-12-31T23:59:59Z")

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
        val parsedDate = testUnit.stringToInstant(dateString)

        // THEN the result is correct
        assertEquals(dateInstant, parsedDate)

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
        val formattedDate = testUnit.instantToString(dateInstant)

        // THEN the result is correct
        assertEquals(dateString, formattedDate)

    }

}
