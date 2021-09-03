package com.retheviper.kotlintools.nio

import java.nio.file.Files
import java.nio.file.Path

/**
 * Creates a new and empty file with its parent directories.
 */
fun Path.createIfNotExists() {
    if (Files.notExists(this)) {
        val parent = this.parent
        if (Files.notExists(parent)) {
            Files.createDirectories(parent)
        }
        Files.createFile(this)
    }
}