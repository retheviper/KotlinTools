package com.retheviper.kotlintools.type

import java.math.BigDecimal

/**
 * If minus, set 0 instead.
 */
fun Int.zeroIfMinus(): Int =
    coerceAtLeast(0)

/**
 * If minus, set 0 instead.
 */
fun Long.zeroIfMinus(): Long =
    coerceAtLeast(0L)

/**
 * If minus, set 0.0 instead.
 */
fun Float.zeroIfMinus(): Float =
    coerceAtLeast(0.0F)

/**
 * If minus, set 0.0 instead.
 */
fun Double.zeroIfMinus(): Double =
    coerceAtLeast(0.0)

/**
 * If minus, set 0 instead.
 */
fun BigDecimal.zeroIfMinus(): BigDecimal =
    coerceAtLeast(BigDecimal(0))

/**
 * If null, set 0 instead.
 */
fun Int?.zeroIfNull(): Int =
    this ?: 0

/**
 * If null, set 0 instead.
 */
fun Long?.zeroIfNull(): Long =
    this ?: 0

/**
 * If null, set 0.0 instead.
 */
fun Float?.zeroIfNull(): Float =
    this ?: 0.0F

/**
 * If null, set 0.0 instead.
 */
fun Double?.zeroIfNull(): Double =
    this ?: 0.0

/**
 * If null, set 0 instead.
 */
fun BigDecimal?.zeroIfNull(): BigDecimal =
    this ?: BigDecimal(0)