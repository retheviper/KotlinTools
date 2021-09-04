package com.retheviper.kotlintools.collection

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CollectionToolsTest : FreeSpec({

    "List<List<T>>.flatten" {
        val size = 100
        val list = (1..size).chunked(10)

        val actual = list.flatten()
        actual.size shouldBe size
    }

    "List<T>.swapped" - {

        val list = listOf("A", "B", "C", "D")
        val expected = listOf("D", "B", "C", "A")

        "List<T>.swapped(Int, Int)" {
            val actual = list.swappedByIndex(0, 3)
            actual shouldBe expected
        }

        "List<T>.swapped(() -> Boolean, () -> Boolean)" {
            val actual = list.swappedByCondition({ it == "A" }, { it == "D" })
            actual shouldBe expected
        }
    }
})