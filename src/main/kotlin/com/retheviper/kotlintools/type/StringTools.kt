package com.retheviper.kotlintools.type

/**
 * If null, set "" instead.
 */
fun String?.ifNullToBlank(): String =
    this ?: ""