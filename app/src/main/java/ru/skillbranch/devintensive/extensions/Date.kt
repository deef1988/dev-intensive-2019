package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(valuse: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time +=when (units) {
        TimeUnits.SECOND -> valuse * SECOND
        TimeUnits.MINUTE -> valuse * MINUTE
        TimeUnits.HOUR -> valuse * HOUR
        TimeUnits.DAY -> valuse * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}