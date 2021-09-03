package com.retheviper.kotlintools.collection

/**
 * Concat List witch was split by [List.chunked].
 */
fun <T> List<List<T>>.flatten(): List<T> =
    reduce { acc, list -> acc + list }

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