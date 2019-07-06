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



enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            SECOND -> pluralSeconds(value)
            MINUTE -> pluralMinutes(value)
            HOUR -> pluralHours(value)
            else -> pluralDays(value)
        }
    }
}



private fun pluralDays(value: Int): String {
    return when {
        isSpecialDiapason(value.toLong()) -> "$value дней"
        isTwoToFourDiapason(value.toLong()) -> "$value дня"
        isOne(value.toLong()) -> "$value день"
        else -> "$value дней"
    }
}

private fun pluralHours(value: Int): String {
    return when {
        isSpecialDiapason(value.toLong()) -> "$value часов"
        isTwoToFourDiapason(value.toLong()) -> "$value часа"
        isOne(value.toLong()) -> "$value час"
        else -> "$value часов"
    }
}

private fun pluralMinutes(value: Int): String {
    return when {
        isSpecialDiapason(value.toLong()) -> "$value минут"
        isTwoToFourDiapason(value.toLong()) -> "$value минуты"
        isOne(value.toLong()) -> "$value минуту"
        else -> "$value минут"
    }
}

private fun pluralSeconds(value: Int): String {
    return when {
        isSpecialDiapason(value.toLong()) -> "$value секунд"
        isTwoToFourDiapason(value.toLong()) -> "$value секунды"
        isOne(value.toLong()) -> "$value секунду"
        else -> "$value секунд"
    }
}

private fun isOne(days: Long): Boolean {
    val strDays = "$days"
    val lastSymbol = strDays[strDays.length - 1].toString().toInt()

    return lastSymbol == 1
}

private fun isTwoToFourDiapason(days: Long): Boolean {
    val strDays = "$days"
    val lastIndex = strDays.length - 1
    val lastSymbol = strDays[lastIndex].toString().toInt()

    return (lastSymbol.coerceIn(2, 4) == lastSymbol)
}

private fun isSpecialDiapason(days: Long): Boolean {
    if (days < 10L) {
        return false
    }

    val strDays = "$days"
    val lastIndex = strDays.length - 1
    val firstSymbol = strDays[lastIndex - 1]
    val secondSymbol = strDays[lastIndex]
    val newSymbol = "$firstSymbol$secondSymbol".toInt()
    return (newSymbol.coerceIn(10, 20) == newSymbol)
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