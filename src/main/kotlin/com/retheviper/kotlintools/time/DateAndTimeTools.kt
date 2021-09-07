package com.retheviper.kotlintools.time

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal

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