package com.retheviper.kotlintools.nio

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.time.LocalDateTime
import java.util.UUID
import java.util.zip.ZipInputStream

class NioToolsTest : FreeSpec({

    val parent = Path.of("/", "tmp", "kotlin", "tools")

    "Path.createIfNotExists" {
        val file = Path.of(UUID.randomUUID().toString() + LocalDateTime.now())

        val path = parent.resolve(file).apply {
            createIfNotExists()
        }

        val actual = Files.exists(path)
        actual shouldBe true
    }

    "Path.zipTo" {
        val source = parent.resolve("zip").apply {
            Files.createDirectories(this)
        }

        val files = (1..9).map {
            val file = source.resolve("test$it.txt")
            val content = UUID.randomUUID().toString().toByteArray()
            Files.write(file, content).toString()
        }

        val zip = parent.resolve("result.zip")

        source.zipTo(zip)

        val unzipPath = Path.of("/tmp", "unzip").also {
            if (Files.notExists(it)) {
                Files.createDirectory(it)
            }
        }

        withContext(Dispatchers.IO) {
            Files.size(zip) shouldNotBe 0L

            ZipInputStream(Files.newInputStream(zip)).use {
                var zipEntry = it.nextEntry
                while (zipEntry != null) {
                    if (!zipEntry!!.isDirectory) {
                        val original = Path.of(zipEntry!!.name)
                        files.contains(original.toString()) shouldBe true

                        val unzippedFile = unzipPath.resolve(original.fileName)
                        Files.copy(it, unzippedFile, StandardCopyOption.REPLACE_EXISTING)
                        Files.size(original) shouldBe Files.size(unzippedFile)
                    }
                    it.closeEntry()
                    zipEntry = it.nextEntry
                }
            }
        }
    }

    afterTest {
        withContext(Dispatchers.IO) {
            Files.walk(parent)
                .sorted(Comparator.reverseOrder())
                .forEach { Files.deleteIfExists(it) }
        }
    }
})