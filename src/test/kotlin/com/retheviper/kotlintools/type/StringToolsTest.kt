package com.retheviper.kotlintools.type

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class StringToolsTest: FreeSpec({

    "String.blankIfNull" {
        val string: String? = null
        val actual = string.blankIfNull()
        actual shouldBe ""
    }
})