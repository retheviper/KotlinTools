package com.retheviper.kotlintools.io

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IoToolsTest : FreeSpec({

    "pipedStreams" {
        val result = "This is result string"

        val (inputStream, outputStream) = pipedStreams()

        outputStream.use {
            withContext(Dispatchers.IO) {
                it.write(result.toByteArray())
            }
        }

        val actual = inputStream.use {
            withContext(Dispatchers.IO) {
                String(inputStream.readAllBytes())
            }
        }

        actual shouldBe result
    }
})