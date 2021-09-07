package com.retheviper.kotlintools.collection

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CollectionToolsTest : FreeSpec({

    "List<T>.swapped" - {

        val list = listOf("A", "B", "C", "D")
        val expected = listOf("D", "B", "C", "A")

        "List<T>.swapped(Int, Int)" {
            val actual = list.swappedByIndex(0, 3)
            actual shouldBe expected
        }

        "List<T>.swapped((T) -> Boolean, (T) -> Boolean)" {
            val actual = list.swappedByCondition({ it == "A" }, { it == "D" })
            actual shouldBe expected
        }
    }
})