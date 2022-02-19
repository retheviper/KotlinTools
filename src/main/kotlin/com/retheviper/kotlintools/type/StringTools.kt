package com.retheviper.kotlintools.type

/**
 * If null, set "" instead.
 */
fun String?.blankIfNull(): String =
    this ?: ""