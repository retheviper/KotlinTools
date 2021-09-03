package com.retheviper.kotlintools.time

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeParseException

class DateAndTimeToolsTest : FreeSpec({

    val yearMonths = (1..12).map {
        YearMonth.of(2021, it)
    }

    "LocalDate.isEndOfMonth" - {
        "LocalDate.isEndOfMonth: true" {
            val dates = yearMonths.map { it.atEndOfMonth() }

            dates.forEach {
                val actual = it.isEndOfMonth()
                actual shouldBe true
            }
        }

        "LocalDate.isEndOfMonth: false" {
            val dates = yearMonths.flatMap {
                (1 until it.lengthOfMonth()).map { day ->
                    it.atDay(day)
                }
            }

            dates.forEach {
                val actual = it.isEndOfMonth()
                actual shouldBe false
            }
        }
    }

    val dates = yearMonths.flatMap {
        (1..it.lengthOfMonth()).map { day ->
            it.atDay(day)
        }
    }

    "LocalDate.toYearMonth" {
        val actual = dates.map { it.toYearMonth() }.distinct()
        actual shouldBe yearMonths
    }

    "LocalDateTime.atStartOfDay" {
        dates.forEach {
            val actual = it.atTime(10, 10, 10).atStartOfDay()
            val expected = it.atStartOfDay()
            actual shouldBe expected
        }
    }

    val formatter = DecimalFormat("00")
    val localDateToInt =
        { it: LocalDate -> "${it.year}${formatter.format(it.monthValue)}${formatter.format(it.dayOfMonth)}".toInt() }

    "Int.toLocalDate" - {
        "Int.toLocalDate: success" {
            dates.forEach {
                val actual = localDateToInt(it).toLocalDate()
                actual shouldBe it
            }
        }

        "Int.toLocalDate: fail" {
            shouldThrow<DateTimeParseException> {
                100.toLocalDate()
            }
        }
    }

    "LocalDate.toInt" {
        dates.forEach {
            val actual = it.toInt()
            val expected = localDateToInt(it)
            actual shouldBe expected
        }
    }

    val yearMonthToInt =
        { it: YearMonth -> "${it.year}${formatter.format(it.monthValue)}".toInt() }

    "Int.toYearMonth" - {
        "Int.toYearMonth: success" {
            yearMonths.forEach {
                val actual = yearMonthToInt(it).toYearMonth()
                actual shouldBe it
            }
        }

        "Int.toYearMonth: fail" {
            shouldThrow<DateTimeParseException> {
                100.toYearMonth()
            }
        }
    }

    "YearMonth.toInt" {
        yearMonths.forEach {
            val actual = it.toInt()
            val expected = yearMonthToInt(it)
            actual shouldBe expected
        }
    }
})