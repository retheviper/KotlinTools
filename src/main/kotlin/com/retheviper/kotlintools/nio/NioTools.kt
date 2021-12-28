package com.retheviper.kotlintools.nio

import java.io.InputStream
import java.io.OutputStream
import java.nio.file.CopyOption
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.FileAttribute
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * @see Files.createFile
 */
fun Path.createFile(vararg attrs: FileAttribute<Any>): Path = Files.createFile(this, *attrs)

/**
 * @see Files.createDirectories
 */
fun Path.createDirectories(vararg attrs: FileAttribute<Any>): Path = Files.createDirectories(this, *attrs)

/**
 * @see Files.exists
 */
fun Path.exists(vararg options: LinkOption): Boolean = Files.exists(this, *options)

/**
 * @see Files.notExists
 */
fun Path.notExists(vararg options: LinkOption): Boolean = Files.notExists(this, *options)

/**
 * @see Files.isHidden
 */
fun Path.isHidden(): Boolean = Files.isHidden(this)

/**
 * @see Files.isDirectory
 */
fun Path.isDirectory(vararg options: LinkOption): Boolean = Files.isDirectory(this, *options)

/**
 * @see Files.copy
 */
fun InputStream.copy(target: Path, vararg options: CopyOption): Long = Files.copy(this, target, *options)

/**
 * @see Files.copy
 */
fun Path.copy(out: OutputStream): Long = Files.copy(this, out)

/**
 * @see Files.copy
 */
fun Path.copy(target: Path, vararg options: StandardCopyOption): Path = Files.copy(this, target, *options)

/**
 * @see Files.move
 */
fun Path.move(target: Path, vararg options: StandardCopyOption): Path = Files.move(this, target, *options)

/**
 * @see Files.size
 */
fun Path.size(): Long = Files.size(this)

/**
 * @see Files.delete
 */
fun Path.delete() = Files.delete(this)

/**
 * @see Files.deleteIfExists
 */
fun Path.deleteIfExists(): Boolean = Files.deleteIfExists(this)

/**
 * Creates a new and empty file with its parent directories.
 */
fun Path.createIfNotExists(vararg attrs: FileAttribute<Any>): Path {
    if (Files.notExists(parent)) {
        Files.createDirectories(parent)
    }
    return if (Files.notExists(this)) {
        Files.createFile(this, *attrs)
    } else {
        this
    }
}

/**
 * ZIP current path to [target].
 */
fun Path.zipTo(target: Path) {
    require(!Files.isDirectory(target)) {
        "Target Path is directory"
    }
    require(!target.fileName.endsWith(".zip")) {
        "Target's extension must be '.zip'"
    }
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