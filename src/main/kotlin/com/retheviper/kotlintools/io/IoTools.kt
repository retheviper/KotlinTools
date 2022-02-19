package com.retheviper.kotlintools.io

import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.nio.ByteBuffer

/**
 * Creates a Pair of [PipedInputStream] and [PipedOutputStream] which has [connected][PipedOutputStream.connect].
 */
fun pipedStreams(): Pair<InputStream, OutputStream> =
    PipedInputStream().let {
        it to PipedOutputStream(it)
    }

fun InputStream.toPipedStreams(): Pair<InputStream, OutputStream> {
    return pipedStreams().apply {
        use { transferTo(second) }
    }
}

fun ByteArrayOutputStream.toPipedStreams(): Pair<InputStream, OutputStream> {
    return pipedStreams().apply {
        use { second.write(toByteArray()) }
    }
}

fun FileOutputStream.toPipedStreams(): Pair<InputStream, OutputStream> {
    return pipedStreams().apply {
        val buffer = ByteBuffer.allocate(1024)
        channel.use { it.read(buffer) }
        second.write(buffer.array())
    }
}