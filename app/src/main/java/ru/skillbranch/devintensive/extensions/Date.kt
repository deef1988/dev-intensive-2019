package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*
import java.math.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 360 * DAY

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
        TimeUnits.YEAR -> valuse * YEAR
    }
    this.time = time
    return this
}

//fun Date.humanizeDiff(date: Date = Date()): String {
//    var time = this.time
//    var delta = (time - date.time)
//    var str = ""
//
//
//    if (delta > 0) {
//        delta = (delta+50) / SECOND
//        println(delta)
//        str += when (delta) {
//            in 0..1 -> "только что"
//            in 2..45 -> "несколько секунд назад"
//            in 46..75 -> "минуту назад"
//            in 76..2700 -> "${delta/60} ${get_name_time(delta/60, TimeUnits.MINUTE)} назад"
//            in 2701..4500 -> "час назад"
//            in 4501..79200 -> "${delta/60/60} ${get_name_time(delta/60/60, TimeUnits.HOUR)} назад"
//            in 79200..93600 -> "день назад"
//            in 93600..77760000 -> "${delta/60/60/60} ${get_name_time(delta/60/60/60, TimeUnits.DAY)} назад"
//            else -> "более года назад"
//        }
//    } else {
//        delta = (delta) / SECOND
//        str += when (delta) {
//            in 0..1 -> "только что будет"
//            in 2..45 -> "более несколько секунд назад"
//            in 46..75 -> "более минуты назад"
//            in 76..2700 -> "более ${delta/60} ${get_name_time(delta/60, TimeUnits.MINUTE)} назад"
//            in 2701..4500 -> "более часа назад"
//            in 4501..79200 -> "более ${delta/60/60} ${get_name_time(delta/60/60, TimeUnits.HOUR)} назад"
//            in 79200..93600 -> "более деня назад"
//            in 93600..77760000 -> "более ${delta/60/60/60} ${get_name_time(delta/60/60/60, TimeUnits.DAY)} назад"
//            else -> "более чем через год"
//        }
////        str = "123"
//    }
//
//    return "${str}"
//}
fun Date.humanizeDiff(date: Date = Date()): String {
    val dif = this.time - date.time

    var interval = when {
        abs(dif) / SECOND.toDouble() <= 1 -> {
            "только что"
        }
        abs(dif) / SECOND.toDouble() <= 45 -> {
            "несколько секунд"
        }
        abs(dif) / SECOND.toDouble() <= 75 -> {
            "минуту назад"
        }
        abs(dif) / MINUTE.toDouble() <= 45 -> {
            "${(abs(dif) / MINUTE).toInt()} ${getNumForm((dif / MINUTE).toInt(), TimeUnits.MINUTE)}"
        }
        abs(dif) / MINUTE.toDouble() <= 75 -> {
            "час назад"
        }
        abs(dif) / HOUR.toDouble() <= 22 -> {
            "${(abs(dif) / HOUR).toInt()} ${getNumForm((dif / HOUR).toInt(), TimeUnits.HOUR)}"
        }
        abs(dif) / HOUR.toDouble() <= 26 -> {
            "день назад"
        }
        abs(dif) / DAY.toDouble() <= 360 -> {
            "${(abs(dif) / DAY).toInt()} ${getNumForm((dif / DAY).toInt(), TimeUnits.DAY)}"
        }
        else -> {
            ""
        }
    }

    when {
        dif / DAY.toDouble() < -360 -> interval = "более года назад"
        dif / SECOND.toDouble() < -1 -> interval += " назад"
        dif / SECOND.toDouble() <= 1 -> {
        }
        dif / DAY.toDouble() <= 360 -> interval = "через $interval"
        else -> interval = "более чем через год"
    }

    return interval
}

fun getNumForm(amount: Int, units: TimeUnits): String {
    val posAmount = abs(amount)

    return when (posAmount) {
        0, in 5..19 -> Plurals.MANY.get(units)
        1 -> Plurals.ONE.get(units)
        in 2..4 -> Plurals.FEW.get(units)
        else -> getNumForm(reduceDigitsNum(posAmount), units)
    }
}

fun get_name_time(time: Long, units: TimeUnits = TimeUnits.SECOND): String {
//    println("time   ${time}")
    val str = when (units) {
        TimeUnits.YEAR -> if (time < 4) {
            "года"
        } else {
            "лет"
        }
        TimeUnits.DAY -> if (time < 4) {
            "дня"
        } else {
            "дней"
        }
        TimeUnits.HOUR -> if (time < 4) {
            "часа"
        } else {
            "часов"
        }
        TimeUnits.MINUTE -> if (time < 4) {
            "минуты"
        } else {
            "минут"
        }
        else -> ""
    }
    return "${str}"
}

private fun reduceDigitsNum(number: Int): Int {
    return number % (10.0.pow((number.toString().length - 1).toDouble())).toInt()
}

enum class Plurals(private val minute: String, private val hour: String, private val day: String) {
    ONE("минуту", "час", "день"),
    FEW("минуты", "часа", "дня"),
    MANY("минут", "часов", "дней");

    fun get(unit: TimeUnits): String {
        return when (unit) {
            TimeUnits.MINUTE -> minute
            TimeUnits.HOUR -> hour
            TimeUnits.DAY -> day
            else -> ""
        }
    }
}

enum class TimeUnits {
    YEAR,
    SECOND,
    MINUTE,
    HOUR,
    DAY
}