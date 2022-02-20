package com.retheviper.kotlintools.nio

import java.io.InputStream
import java.io.OutputStream
import java.nio.file.CopyOption
import java.nio.file.FileStore
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.nio.file.attribute.FileAttribute
import java.util.stream.Stream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * @see Files.createFile
 */
fun Path.createFile(vararg attrs: FileAttribute<Any>): Path =
    Files.createFile(this, *attrs)

/**
 * @see Files.createDirectories
 */
fun Path.createDirectories(vararg attrs: FileAttribute<Any>): Path =
    Files.createDirectories(this, *attrs)

/**
 * @see Files.copy
 */
fun InputStream.copy(to: Path, vararg options: CopyOption): Long =
    Files.copy(this, to, *options)

/**
 * @see Files.copy
 */
fun Path.copy(to: OutputStream): Long =
    Files.copy(this, to)

/**
 * @see Files.copy
 */
fun Path.copy(to: Path, vararg options: StandardCopyOption): Path =
    Files.copy(this, to, *options)

/**
 * @see Files.move
 */
fun Path.move(to: Path, vararg options: StandardCopyOption): Path =
    Files.move(this, to, *options)

/**
 * @see Files.delete
 */
fun Path.delete() =
    Files.delete(this)

/**
 * @see Files.deleteIfExists
 */
fun Path.deleteIfExists(): Boolean =
    Files.deleteIfExists(this)

/**
 * @see Files.exists
 */
fun Path.exists(vararg options: LinkOption): Boolean =
    Files.exists(this, *options)

/**
 * @see Files.notExists
 */
fun Path.notExists(vararg options: LinkOption): Boolean =
    Files.notExists(this, *options)

/**
 * @see Files.isDirectory
 */
fun Path.isDirectory(vararg options: LinkOption): Boolean =
    Files.isDirectory(this, *options)

/**
 * @see Files.isSameFile
 */
fun Path.isSameFile(other: Path): Boolean =
    Files.isSameFile(this, other)

/**
 * @see Files.walk
 */
fun Path.walk(vararg options: FileVisitOption): Stream<Path> =
    Files.walk(this, *options)

/**
 * @see Files.isHidden
 */
val Path.isHidden: Boolean
    get() = Files.isHidden(this)

/**
 * @see Files.size
 */
val Path.size: Long
    get() = Files.size(this)

/**
 * @see Files.isExecutable
 */
val Path.isExecutable: Boolean
    get() = Files.isExecutable(this)

/**
 * @see Files.isReadable
 */
val Path.isReadable: Boolean
    get() = Files.isReadable(this)

/**
 * @see Files.isSymbolicLink
 */
val Path.isSymbolicLink: Boolean
    get() = Files.isSymbolicLink(this)

/**
 * @see Files.isWritable
 */
val Path.isWritable: Boolean
    get() = Files.isWritable(this)

/**
 * @see Files.getFileStore
 */
val Path.fileStore: FileStore
    get() = Files.getFileStore(this)

/**
 * @see Files.list
 */
val Path.list: Stream<Path>
    get() = Files.list(this)

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