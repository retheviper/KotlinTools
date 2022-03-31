package com.retheviper.kotlintools.collection

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CollectionToolsTest : FreeSpec({

    val list = listOf("A", "B", "C", "D")
    val map = mapOf("a" to "A", "b" to "B", "c" to "C", "d" to "D")

    "Collection<T>.swapped" - {

        val expected = listOf("D", "B", "C", "A")

        "Collection<T>.swappedByIndex(Int, Int)" {
            val actual = list.swappedByIndex(0, 3)
            actual shouldBe expected
        }

        "Collection<T>.swappedByCondition((T) -> Boolean, (T) -> Boolean)" {
            val actual = list.swappedByCondition({ it == "A" }, { it == "D" })
            actual shouldBe expected
        }

        "Collection<T>.swappedByElement((T) -> Boolean, (T) -> Boolean)" {
            val actual = list.swappedByElements("A", "D")
            actual shouldBe expected
        }
    }

    "Collection<T>.until" - {
        "Collection<T>.forUntil((Int, T) → Boolean, (Int, T) → Unit)" {
            val expected = 2
            var actual = 0
            list.forUntil({ index, _ -> index < 2 }, { _, _ -> actual++ })
            actual shouldBe expected
        }

        "Collection<T>.mapUntil((Int, T) → Boolean, (T) → R)" {
            val expected = listOf("a", "b")
            val actual = list.mapUntil({ index, _ -> index < 2 }, { it.lowercase() })
            actual shouldBe expected
        }
    }

    "Map<K, V>.until" - {
        "Map<K, V>.forUntil((K, V) → Boolean, (K, V) → Unit)" {
            val expected = 2
            var actual = 0
            map.forUntil({ key, value -> key != "c" || value != "C" }, { _, _ -> actual++ })
            actual shouldBe expected
        }

        "Map<K, V>.mapUntil((K, V) → Boolean, (K, V) → Unit)" {
            val expected = listOf("a: A", "b: B")
            val actual = map.mapUntil({ key, value -> key != "c" || value != "C" }, { key, value -> "$key: $value" })
            actual shouldBe expected
        }
    }

    "List<T>.divide" {
        val expected = listOf(listOf("A", "B", "C", "D"), listOf("E", "F", "G"), listOf("H", "I", "J"))
        val actual = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J").divide(3)
        actual shouldBe expected
    }
})