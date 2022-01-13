package com.retheviper.kotlintools.type

import java.math.BigDecimal

/**
 * If minus, set 0 instead.
 */
fun Int.asNonnegative(): Int =
    this.coerceAtLeast(0)

/**
 * If minus, set 0 instead.
 */
fun Long.asNonnegative(): Long =
    this.coerceAtLeast(0L)

/**
 * If minus, set 0.0 instead.
 */
fun Float.asNonnegative(): Float =
    this.coerceAtLeast(0.0F)

/**
 * If minus, set 0.0 instead.
 */
fun Double.asNonnegative(): Double =
    this.coerceAtLeast(0.0)

/**
 * If minus, set 0 instead.
 */
fun BigDecimal.asNonnegative(): BigDecimal =
    this.coerceAtLeast(BigDecimal(0))

/**
 * If null, set 0 instead.
 */
fun Int?.ifNullToZero(): Int =
    this ?: 0

/**
 * If null, set 0 instead.
 */
fun Long?.ifNullToZero(): Long =
    this ?: 0

/**
 * If null, set 0.0 instead.
 */
fun Float?.ifNullToZero(): Float =
    this ?: 0.0F

/**
 * If null, set 0.0 instead.
 */
fun Double?.ifNullToZero(): Double =
    this ?: 0.0

/**
 * If null, set 0 instead.
 */
fun BigDecimal?.ifNullToZero(): BigDecimal =
    this ?: BigDecimal(0)