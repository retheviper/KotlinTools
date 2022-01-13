package com.retheviper.kotlintools.type

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class NumericToolsTest: FreeSpec({

    "Number.asNonnegative" - {
        "Int.asNonnegative" {
            val number = 10 - 20
            val actual = number.asNonnegative()
            actual shouldBe 0
        }

        "Long.asNonnegative" {
            val number = 10L - 20L
            val actual = number.asNonnegative()
            actual shouldBe 0L
        }

        "Float.asNonnegative" {
            val number = 10.0F - 20.0F
            val actual = number.asNonnegative()
            actual shouldBe 0.0F
        }

        "Double.asNonnegative" {
            val number = 10.0 - 20.0
            val actual = number.asNonnegative()
            actual shouldBe 0.0
        }

        "BigDecimal.asNonnegative" {
            val number = BigDecimal(10) - BigDecimal(20)
            val actual = number.asNonnegative()
            actual shouldBe BigDecimal(0)
        }
    }

    "Number.ifNullToZero" - {
        "Int.ifNullToZero" {
            val number: Int? = null
            val actual = number.ifNullToZero()
            actual shouldBe 0
        }

        "Long.ifNullToZero" {
            val number: Long? = null
            val actual = number.ifNullToZero()
            actual shouldBe 0L
        }

        "Float.ifNullToZero" {
            val number: Float? = null
            val actual = number.ifNullToZero()
            actual shouldBe 0.0F
        }

        "Double.ifNullToZero" {
            val number: Double? = null
            val actual = number.ifNullToZero()
            actual shouldBe 0.0
        }

        "BigDecimal.ifNullToZero" {
            val number: BigDecimal? = null
            val actual = number.ifNullToZero()
            actual shouldBe BigDecimal(0)
        }
    }
})