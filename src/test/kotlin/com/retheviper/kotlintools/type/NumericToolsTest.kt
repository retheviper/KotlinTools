package com.retheviper.kotlintools.type

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class NumericToolsTest: FreeSpec({

    "Number.zeroIfMinus" - {
        "Int.zeroIfMinus" {
            val number = 10 - 20
            val actual = number.zeroIfMinus()
            actual shouldBe 0
        }

        "Long.zeroIfMinus" {
            val number = 10L - 20L
            val actual = number.zeroIfMinus()
            actual shouldBe 0L
        }

        "Float.zeroIfMinus" {
            val number = 10.0F - 20.0F
            val actual = number.zeroIfMinus()
            actual shouldBe 0.0F
        }

        "Double.zeroIfMinus" {
            val number = 10.0 - 20.0
            val actual = number.zeroIfMinus()
            actual shouldBe 0.0
        }

        "BigDecimal.zeroIfMinus" {
            val number = BigDecimal(10) - BigDecimal(20)
            val actual = number.zeroIfMinus()
            actual shouldBe BigDecimal(0)
        }
    }

    "Number.zeroIfNull" - {
        "Int.zeroIfNull" {
            val number: Int? = null
            val actual = number.zeroIfNull()
            actual shouldBe 0
        }

        "Long.zeroIfNull" {
            val number: Long? = null
            val actual = number.zeroIfNull()
            actual shouldBe 0L
        }

        "Float.zeroIfNull" {
            val number: Float? = null
            val actual = number.zeroIfNull()
            actual shouldBe 0.0F
        }

        "Double.zeroIfNull" {
            val number: Double? = null
            val actual = number.zeroIfNull()
            actual shouldBe 0.0
        }

        "BigDecimal.zeroIfNull" {
            val number: BigDecimal? = null
            val actual = number.zeroIfNull()
            actual shouldBe BigDecimal(0)
        }
    }
})