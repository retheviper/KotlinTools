package com.retheviper.kotlintools.nio

import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Creates a new and empty file with its parent directories.
 */
fun Path.createIfNotExists() {
    if (Files.notExists(parent)) {
        Files.createDirectories(parent)
    }
    if (Files.notExists(this)) {
        Files.createFile(this)
    }
}

/**
 * ZIP current path to [target].
 */
fun Path.zipTo(target: Path) {
    ZipOutputStream(Files.newOutputStream(target)).use { zip ->
        Files.walk(this)
            .filter { Files.isHidden(it).not() }
            .forEach {
                if (Files.isDirectory(it)) {
                    zip.putNextEntry(ZipEntry("$it/"))
                    zip.closeEntry()
                } else {
                    zip.putNextEntry(ZipEntry(it.toString()))
                    Files.copy(it, zip)
                }
            }
    }
}