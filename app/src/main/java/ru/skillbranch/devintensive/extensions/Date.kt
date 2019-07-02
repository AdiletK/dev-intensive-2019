package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import kotlin.math.abs
import kotlin.math.roundToInt

const val SECOND =1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"):String{
    val dateFormat = java.text.SimpleDateFormat(pattern, Locale("ru "))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time += when(unit){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
        val diff = this.time - date.time
         return if (diff >= 0) {
            humanizeFuture(abs(diff))
         } else {
            humanizePast(abs(diff))
         }
 }
private fun humanizeFuture(diff: Long): String {
    return when (diff) {
        in 0..(1 * SECOND) -> "только что"
        in (1 * SECOND + 1)..(45 * SECOND) -> "через несколько секунд"
        in (45 * SECOND + 1)..(75 * SECOND) -> "через минуту"
        in (75 * SECOND + 1)..(45 * MINUTE) -> {
            val amount = calcDiffAmount(diff, MINUTE)
            "через $amount ${Utils.getPluralForm("минуту;минуты;минут", amount)}"
        }
        in (45 * MINUTE + 1)..(75 * MINUTE) -> "через час"
        in (75 * MINUTE + 1)..(22 * HOUR) -> {
            val amount = calcDiffAmount(diff, HOUR)
            "через $amount ${Utils.getPluralForm("час;часа;часов", amount)}"
        }
        in (22 * HOUR + 1)..(26 * HOUR) -> "через день"
        in (26 * HOUR + 1)..(360 * DAY) -> {
            val amount = calcDiffAmount(diff, DAY)
            "через $amount ${Utils.getPluralForm("день;дня;дней", amount)}"
        }
        else -> "более чем через год"
    }
}


fun getFutureVisit(
    seconds: Long,
    minutes: Long,
    hours: Long,
    days: Long,
    week: Long,
    month: Int,
    year: Double,
    date: Date
): String {
    when {
        seconds < 60 -> return "через несколько секунд"
        minutes < 60 -> return if (minutes.toInt() == 1) {
            "через $minutes минуту"
        } else {
            when (minutes % 5) {
                in 2..4 -> "через $minutes минуты"
                else -> "через $minutes минут"
            }
        }
        hours < 24 -> return if (hours.toInt() == 1) {
            "через час назад"
        } else {
            return when ((hours)) {
                in 2..4 -> "через $hours часа"
                else -> "через $hours часов"
            }
        }
        days < 7 -> return if (days.toInt() == 1) {
            "через день"
        } else {
            return when (days) {
                in 2..4 -> "через $days дня"
                else -> "через $days дней"
            }
        }
        week < 4.3 -> return if (week.toInt() == 1) {
            "через неделю"
        } else {
            "через $week недели"
        }
        month < 12 -> return if (month == 1 || month < 2) {
            "через более месяца"
        } else {
            when (month) {
                in 2..4 -> "через $month месяца "
                else -> "через $month месяцев "
            }
        }
        year >= 1 -> return "через  год"
        else -> return "через довольно давно ${date.format("dd:MM:yyyy")}"
    }
}

private fun getPastVisit(
    seconds: Long,
    minutes: Long,
    hours: Long,
    days: Long,
    week: Long,
    month: Int,
    year: Double,
    date: Date
): String {
    when {
        seconds < 60 -> return "несколько секунд назад"
        minutes < 60 -> return if (minutes.toInt() == 1) {
            "$minutes минуту назад"
        } else {
            when (minutes % 5) {
                in 2..4 -> "$minutes минуты назад"
                else -> "$minutes минут назад"
            }
        }
        hours < 24 -> return if (hours.toInt() == 1) {
            "час назад"
        } else {
            return when (hours) {
                in 2..4 -> "$hours часа назад"
                else -> "$hours часов назад"
            }
        }
        days < 7 -> return if (days.toInt() == 1) {
            "день назад"
        } else {
            return when (days) {
                in 2..4 -> "$days дня назад"
                else -> "$days дней назад"
            }
        }
        week < 4.3 -> return if (week.toInt() == 1) {
            "неделю назад"
        } else {
            "$week недели назад"
        }
        month < 12 -> return if (month.toInt() == 1 || month < 2) {
            "более месяца назад"
        } else {
            when (month) {
                in 2..4 -> "$month месяца назад"
                else -> "$month месяцев назад"
            }
        }
        year >= 1 -> return "более года назад"
        else -> return "довольно давно ${date.format("dd:MM:yyyy")}"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

private fun humanizePast(diff: Long): String {
    return when (diff) {
        in 0..(1 * SECOND) -> "только что"
        in (1 * SECOND + 1)..(45 * SECOND) -> "несколько секунд назад"
        in (45 * SECOND + 1)..(75 * SECOND) -> "минуту назад"
        in (75 * SECOND + 1)..(45 * MINUTE) -> {
            val amount = calcDiffAmount(diff, MINUTE)
            "$amount ${Utils.getPluralForm("минуту;минуты;минут", amount)} назад"
        }
        in (45 * MINUTE + 1)..(75 * MINUTE) -> "час назад"
        in (75 * MINUTE + 1)..(22 * HOUR) -> {
            val amount = calcDiffAmount(diff, HOUR)
            "$amount ${Utils.getPluralForm("час;часа;часов", amount)} назад"
        }
        in (22 * HOUR + 1)..(26 * HOUR) -> "день назад"
        in (26 * HOUR + 1)..(360 * DAY) -> {
            val amount = calcDiffAmount(diff, DAY)
            "$amount ${Utils.getPluralForm("день;дня;дней", amount)} назад"
        }
        else -> "более года назад"
    }
}
private fun calcDiffAmount(diff: Long, timeInterval: Long): Int {
    val result: Float = (diff.toFloat() / timeInterval.toFloat())
    return result.roundToInt()
}