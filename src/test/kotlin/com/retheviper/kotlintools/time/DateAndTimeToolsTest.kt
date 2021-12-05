package com.retheviper.kotlintools.time

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.util.*

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

    "LocalDate.minus" {
        val expected = 365L
        val actual = LocalDate.of(2020, 1, 1) - LocalDate.of(2020, 12, 31)
        actual shouldBe expected
    }

    "LocalDate.ageAt" {
        val expected = 20
        val actual = LocalDate.of(2000, 1, 1).ageAt(LocalDate.of(2020, 1, 1))
        actual shouldBe expected
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

    "LocalDate.getJapanese" - {
        val meiji = LocalDate.of(1880, 12, 31)
        val taisho = LocalDate.of(1920, 12, 31)
        val showa = LocalDate.of(1980, 12, 31)
        val heisei = LocalDate.of(2000, 12, 31)
        val reiwa = LocalDate.of(2020, 12, 31)

        "LocalDate.getJapaneseYear" {
            mapOf(
                13 to meiji.getJapaneseYear(),
                9 to taisho.getJapaneseYear(),
                55 to showa.getJapaneseYear(),
                12 to heisei.getJapaneseYear(),
                2 to reiwa.getJapaneseYear(),
            ).forEach { (expected, actual) ->
                actual shouldBe expected
            }
        }

        "LocalDate.getJapaneseEra" {
            mapOf(
                "明治" to meiji.getJapaneseEra(),
                "大正" to taisho.getJapaneseEra(),
                "昭和" to showa.getJapaneseEra(),
                "平成" to heisei.getJapaneseEra(),
                "令和" to reiwa.getJapaneseEra()
            ).forEach { (expected, actual) ->
                actual shouldBe expected
            }

            mapOf(
                "M" to meiji.getJapaneseEra(textStyle = TextStyle.NARROW),
                "T" to taisho.getJapaneseEra(textStyle = TextStyle.NARROW),
                "S" to showa.getJapaneseEra(textStyle = TextStyle.NARROW),
                "H" to heisei.getJapaneseEra(textStyle = TextStyle.NARROW),
                "R" to reiwa.getJapaneseEra(textStyle = TextStyle.NARROW)
            ).forEach { (expected, actual) ->
                actual shouldBe expected
            }

            mapOf(
                "Meiji" to meiji.getJapaneseEra(locale = Locale.ENGLISH),
                "Taisho" to taisho.getJapaneseEra(locale = Locale.ENGLISH),
                "Showa" to showa.getJapaneseEra(locale = Locale.ENGLISH),
                "Heisei" to heisei.getJapaneseEra(locale = Locale.ENGLISH),
                "Reiwa" to reiwa.getJapaneseEra(locale = Locale.ENGLISH)
            ).forEach { (expected, actual) ->
                actual shouldBe expected
            }
        }

        "LocalDate.getJapaneseEraUnicode" {
            mapOf(
                "\u337e" to meiji.getJapaneseEraUnicode(),
                "\u337d" to taisho.getJapaneseEraUnicode(),
                "\u337c" to showa.getJapaneseEraUnicode(),
                "\u337b" to heisei.getJapaneseEraUnicode(),
                "\u32ff" to reiwa.getJapaneseEraUnicode()
            ).forEach { (expected, actual) ->
                actual shouldBe expected
            }
        }
    }
})