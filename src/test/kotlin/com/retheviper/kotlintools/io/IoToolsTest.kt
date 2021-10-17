package com.retheviper.kotlintools.io

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class IoToolsTest : FreeSpec({

    "pipedStreams" {
        val result = "This is result string"

        val (inputStream, outputStream) = pipedStreams()

        outputStream.use {
            runCatching {
                it.write(result.toByteArray())
            }
        }

        val actual = inputStream.use {
            runCatching {
                String(inputStream.readAllBytes())
            }
        }

        actual.getOrNull() shouldBe result
    }
})