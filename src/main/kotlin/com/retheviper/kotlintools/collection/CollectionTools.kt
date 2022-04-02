package com.retheviper.kotlintools.collection

/**
 * Swap index of elements of given indices.
 */
fun <T> Collection<T>.swappedByIndex(indexFrom: Int, indexTo: Int): List<T> =
    toMutableList().apply {
        this[indexFrom] = this[indexTo].also { this[indexTo] = this[indexFrom] }
    }.toList()

/**
 * Swap index of elements of given conditions.
 */
inline fun <reified T> Collection<T>.swappedByCondition(from: (T) -> Boolean, to: (T) -> Boolean): List<T> =
    swappedByIndex(indexOfFirst { from(it) }, indexOfFirst { to(it) })

/**
 * Swap index of elements.
 */
fun <T> Collection<T>.swappedByElements(from: T, to: T): List<T> =
    swappedByIndex(indexOf(from), indexOf(to))

/**
 * Performs the given [action] on element until matching the given [predicate].
 */
inline fun <reified T> Collection<T>.forUntil(predicate: (index: Int, T) -> Boolean, action: (Int, T) -> Unit) {
    for ((index, element) in withIndex()) {
        if (predicate(index, element)) action(index, element) else return
    }
}

/**
 * Returns a list containing the results of applying the given [transform] function to until element matching the given [predicate] in the original collection.
 */
inline fun <reified T, R> Collection<T>.mapUntil(predicate: (index: Int, T) -> Boolean, transform: (T) -> R): List<R> =
    mutableListOf<R>().apply {
        forUntil(predicate) { _, element -> add(transform(element)) }
    }.toList()

/**
 * Performs the given [action] on key-value pairs until matching the given [predicate].
 */
inline fun <reified K, V> Map<K, V>.forUntil(predicate: (K, V) -> Boolean, action: (K, V) -> Unit) {
    for ((key, value) in entries) {
        if (predicate(key, value)) action(key, value) else return
    }
}

/**
 * Returns a list containing the results of applying the given [transform] function to key-value pairs until matching the given [predicate] in the original map.
 */
inline fun <reified K, V, R> Map<K, V>.mapUntil(predicate: (K, V) -> Boolean, transform: (K, V) -> R): List<R> =
    mutableListOf<R>().apply {
        forUntil(predicate) { key, element -> add(transform(key, element)) }
    }.toList()

/**
 * Divide a List to given number of lists.
 */
fun <T> List<T>.divide(number: Int): List<List<T>> {
    val partitionSizes = IntArray(number)
    var offset = 0

    repeat(size) {
        partitionSizes[offset++]++
        if (offset == number) {
            offset = 0
        }
    }

    offset = 0
    return partitionSizes.map { partitionSize ->
        subList(offset, offset + partitionSize).also {
            offset += partitionSize
        }
    }
}

/**
 * Get sublist of given [range] in its indices.
 */
operator fun <T> List<T>.get(range: IntRange): List<T> =
    subList(range.first, range.last + 1)