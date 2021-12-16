package com.retheviper.kotlintools.time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.YearMonth
import java.time.chrono.JapaneseDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.Locale

/**
 * Returns whether the date is the last day of the month.
 */
fun LocalDate.isEndOfMonth(): Boolean = dayOfMonth == lengthOfMonth()

/**
 * Gets the [YearMonth] part of this date.
 */
fun LocalDate.toYearMonth(): YearMonth = YearMonth.from(this)

/**
 * Calculates the amount of days between two [LocalDate]s.
 */
operator fun LocalDate.minus(date: LocalDate): Long = ChronoUnit.DAYS.between(this, date)

/**
 * Calculates age from [LocalDate] to [date].
 */
fun LocalDate.ageAt(date: LocalDate): Int = Period.between(this, date).years

/**
 * Returns this [LocalDateTime] formed from this date at the time of midnight, 00:00, at the start of this date.
 */
fun LocalDateTime.atStartOfDay(): LocalDateTime = toLocalDate().atStartOfDay()

/**
 * Convert [Int] like `20210901` to [LocalDate].
 */
fun Int.toLocalDate(): LocalDate = LocalDate.parse(toString(), DateTimeFormatter.BASIC_ISO_DATE)

/**
 * Convert [LocalDate] to [Int].
 */
fun LocalDate.toInt(): Int = toDigit()

/**
 * Convert [Int] like `202109` to [YearMonth].
 */
fun Int.toYearMonth(): YearMonth = YearMonth.parse(toString(), DateTimeFormatter.ofPattern("yyyyMM"))

/**
 * Convert [YearMonth] to [Int].
 */
fun YearMonth.toInt(): Int = toDigit()

/**
 * Parses Int number from it and returns the result.
 */
private fun Temporal.toDigit(): Int = toString().filter { it.isDigit() }.toInt()

/**
 * Get year value within current japanese Era.
 */
fun LocalDate.getJapaneseYear(): Int = JapaneseDate.from(this).get(ChronoField.YEAR_OF_ERA)

/**
 * Get current japanese era name of its year.
 */
fun LocalDate.getJapaneseEra(textStyle: TextStyle = TextStyle.FULL, locale: Locale = Locale.JAPAN): String =
    JapaneseDate.from(this).era.getDisplayName(textStyle, locale)

/**
 * Get current japanese era name of its year as unicode.
 */
fun LocalDate.getJapaneseEraUnicode(): String =
    when (JapaneseDate.from(this).era.value) {
        -1 -> "\u337e"
        0 -> "\u337d"
        1 -> "\u337c"
        2 -> "\u337b"
        3 -> "\u32ff"
        else -> throw IllegalStateException("not supported")
    }