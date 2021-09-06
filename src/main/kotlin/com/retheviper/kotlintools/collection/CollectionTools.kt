package com.retheviper.kotlintools.collection

/**
 * Swap index of elements of given indices.
 */
fun <T> List<T>.swappedByIndex(indexFrom: Int, indexTo: Int): List<T> =
    toMutableList().apply {
        this[indexFrom] = this[indexTo].also { this[indexTo] = this[indexFrom] }
    }.toList()

/**
 * Swap index of elements of given conditions.
 */
fun <T> List<T>.swappedByCondition(from: (T) -> Boolean, to: (T) -> Boolean): List<T> =
    swappedByIndex(indexOfFirst { from(it) }, indexOfFirst { to(it) })