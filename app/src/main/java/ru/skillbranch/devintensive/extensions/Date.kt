package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.math.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 360 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(valuse: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> valuse * SECOND
        TimeUnits.MINUTE -> valuse * MINUTE
        TimeUnits.HOUR -> valuse * HOUR
        TimeUnits.DAY -> valuse * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var time = this.time
    var delta = (time - date.time) // MINUTE
    var str = ""
    if (delta > 0) {
        delta += 50
        if (delta / YEAR > 0) {
            str = "более чем через год"
        } else if (delta / DAY > 0) {
            val ext = (delta / DAY)
            str = when (delta / DAY) {
                in 0..1 -> "Через один день"
                in 2..4 -> "Через ${ext} дня"
                else -> "Через ${ext} дней"
            }
        } else if (delta / HOUR > 0) {
            val ext = delta / HOUR
            str = when (delta * -1 / HOUR) {
                in 0..1 -> "Через один час"
                in 2..4 -> "Через ${ext} часа"
                else -> "Через ${ext} часов"
            }
        } else if (delta / MINUTE > 0) {
            val ext = delta / MINUTE
            str = when (delta / MINUTE) {
                in 0..1 -> "Через одну минуту"
                in 2..4 -> "Через ${ext} минтуты"
                else -> "Через ${ext} минут"
            }
        } else if (delta / SECOND > 0) {
            val ext = delta / SECOND
            str = when (delta/ SECOND) {
                in 0..1 -> "Через одну секунду"
                in 2..4 -> "Через ${ext} секунды"
                else -> "Через ${ext} сикунд"
            }
        }
    } else {
        if (delta * -1 / YEAR > 0) {
            str = "более года назад"
        }
        if (delta * -1 / DAY > 0) {
            val ext = delta * -1 / DAY
            str = when (delta * -1 / DAY) {
                in 0..1 -> "Один день назад"
                in 2..4 -> "${ext} дня назад"
                else -> "${ext} дней назад"
            }
        } else if (delta * -1 / HOUR > 0) {
            val ext = delta * -1 / HOUR
            str = when (delta * -1 / HOUR) {
                in 0..1 -> "Один час назад"
                in 2..4 -> "${ext} часа назад"
                else -> "${ext} часов назад"
            }
        } else if (delta * -1 / MINUTE > 0) {
            val ext = delta * -1 / DAY
            str = when (delta * -1 / DAY) {
                in 0..1 -> "Один день назад"
                in 2..4 -> "${ext} дня назад"
                else -> "${ext} дней назад"
            }
        } else if (delta * -1 / SECOND > 0) {
            val ext = delta * -1 / MINUTE
            str = when (delta * -1 / MINUTE) {
                in 0..1 -> "Одну минуту назад"
                in 2..4 -> "${ext} минтуты назад"
                else -> "${ext} минут назад"
            }
        } else if (delta / SECOND > 0) {
            val ext = delta * -1 / SECOND
            str = when (delta * -1 / SECOND) {
                in 0..1 -> "Одну секунду назад"
                in 2..4 -> "${ext} секунды назад"
                else -> "${ext} сикунд назад"
            }
    }

    }
    return "${str}"
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}