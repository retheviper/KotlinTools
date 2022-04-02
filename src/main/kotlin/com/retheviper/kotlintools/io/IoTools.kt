package com.retheviper.kotlintools.io

import java.io.InputStream
import java.io.OutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream

/**
 * Creates a Pair of [PipedInputStream] and [PipedOutputStream] which has [connected][PipedOutputStream.connect].
 */
fun pipedStreams(): Pair<InputStream, OutputStream> =
    PipedInputStream().let { it to PipedOutputStream(it) }