package com.retheviper.kotlintools.nio

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDateTime
import java.util.*

class NioToolsTest : FreeSpec({

    "Path.createIfNotExists" {
        val parent = Path.of("/","tmp", "kotlin", "tools")
        val file = Path.of(UUID.randomUUID().toString() + LocalDateTime.now())

        val path = parent.resolve(file).apply {
            createIfNotExists()
        }

        val actual = Files.exists(path)
        actual shouldBe true

        runCatching {
            Files.deleteIfExists(path)
        }
    }
})