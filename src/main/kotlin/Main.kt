@file: Suppress("Warnings")

package com.vervyle

import kotlinx.datetime.*
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import java.time.Month
import kotlin.math.max
import kotlin.math.min

val mon = mapOf(
    4 to 29,
    5 to 37,
    6 to 18,
    7 to 21,
    8 to 29,
    9 to 45,
    10 to 81,
    11 to 100,
    12 to 51,
    13 to 27,
    14 to 27,
    15 to 32,
    16 to 32,
    17 to 13,
    18 to 5,
    19 to 8,
    20 to 16,
    21 to 45,
    22 to 48,
    23 to 78,
    0 to 62,
    1 to 51,
    2 to 29,
    3 to 40
)
val tue = mapOf(
    4 to 40,
    5 to 81,
    6 to 56,
    7 to 51,
    8 to 35,
    9 to 54,
    10 to 59,
    11 to 29,
    12 to 10,
    13 to 5,
    14 to 10,
    15 to 24,
    16 to 10,
    17 to 5,
    18 to 2,
    19 to 8,
    20 to 21,
    21 to 24,
    22 to 35,
    23 to 37,
    0 to 27,
    1 to 24,
    2 to 10,
    3 to 29
)
val wed = mapOf(
    4 to 16,
    5 to 29,
    6 to 54,
    7 to 70,
    8 to 54,
    9 to 62,
    10 to 24,
    11 to 21,
    12 to 27,
    13 to 56,
    14 to 51,
    15 to 43,
    16 to 40,
    17 to 40,
    18 to 40,
    19 to 51,
    20 to 83,
    21 to 97,
    22 to 67,
    23 to 37,
    0 to 24,
    1 to 45,
    2 to 37,
    3 to 27
)
val thu = mapOf(
    4 to 27,
    5 to 40,
    6 to 62,
    7 to 78,
    8 to 72,
    9 to 51,
    10 to 24,
    11 to 18,
    12 to 8,
    13 to 10,
    14 to 24,
    15 to 18,
    16 to 27,
    17 to 32,
    18 to 51,
    19 to 62,
    20 to 29,
    21 to 18,
    22 to 16,
    23 to 40,
    0 to 48,
    1 to 45,
    2 to 24,
    3 to 16
)
val fri = mapOf(
    4 to 5,
    5 to 10,
    6 to 21,
    7 to 21,
    8 to 27,
    9 to 48,
    10 to 32,
    11 to 29,
    12 to 16,
    13 to 24,
    14 to 56,
    15 to 75,
    16 to 91,
    17 to 54,
    18 to 24,
    19 to 5,
    20 to 10,
    21 to 10,
    22 to 29,
    23 to 51,
    0 to 70,
    1 to 51,
    2 to 21,
    3 to 5,
)
val sat = mapOf(
    4 to 2,
    5 to 21,
    6 to 43,
    7 to 67,
    8 to 45,
    9 to 24,
    10 to 13,
    11 to 5,
    12 to 2,
    13 to 0,
    14 to 0,
    15 to 2,
    16 to 5,
    17 to 5,
    18 to 16,
    19 to 37,
    20 to 56,
    21 to 37,
    22 to 32,
    23 to 43,
    0 to 56,
    1 to 45,
    2 to 27,
    3 to 8
)
val sun = mapOf(
    4 to 43,
    5 to 32,
    6 to 18,
    7 to 13,
    8 to 16,
    9 to 35,
    10 to 29,
    11 to 18,
    12 to 10,
    13 to 2,
    14 to 0,
    15 to 0,
    16 to 0,
    17 to 0,
    18 to 0,
    19 to 0,
    20 to 0,
    21 to 0,
    22 to 0,
    23 to 0,
    0 to 18,
    1 to 37,
    2 to 64,
    3 to 43
)

val occupancy = mapOf(
    DayOfWeek.MONDAY to mon,
    DayOfWeek.TUESDAY to tue,
    DayOfWeek.WEDNESDAY to wed,
    DayOfWeek.THURSDAY to thu,
    DayOfWeek.FRIDAY to fri,
    DayOfWeek.SATURDAY to sat,
    DayOfWeek.SUNDAY to sun,
)

val weeks = mapOf(
    1 to 100,  // Январь, праздничный пик
    2 to 100,
    3 to 100,
    4 to 80,   // Январь, спад после праздников
    5 to 75,
    6 to 70,   // Февраль, обычные будни
    7 to 70,
    8 to 70,
    9 to 75,   // Март, небольшое увеличение
    10 to 80,
    11 to 85,  // Весна, рост активности
    12 to 85,
    13 to 90,
    14 to 90,  // Апрель, стабильный уровень
    15 to 85,
    16 to 80,  // Май, снижение после праздников
    17 to 80,
    18 to 75,  // Лето, спад
    19 to 75,
    20 to 70,
    21 to 70,
    22 to 70,  // Июнь
    23 to 70,
    24 to 70,
    25 to 70,  // Июль, стабильно низкая активность
    26 to 70,
    27 to 70,
    28 to 75,  // Август, начало роста
    29 to 80,
    30 to 85,
    31 to 85,  // Сентябрь, возвращение активности
    32 to 90,
    33 to 90,
    34 to 85,
    35 to 85,  // Октябрь, стабилизация
    36 to 80,
    37 to 80,
    38 to 75,  // Ноябрь, снижение
    39 to 75,
    40 to 75,
    41 to 80,  // Декабрь, рост перед праздниками
    42 to 85,
    43 to 90,
    44 to 95,
    45 to 100, // Новый год
    46 to 100,
    47 to 100,
    48 to 90,
    49 to 90,
    50 to 95,  // Декабрь, пик праздников
    51 to 100,
    52 to 100
)

const val LAMBDA_MAX = 150.0

const val SERVICE_LAMBDA = 60.0
const val T = (24.0 * 6 + 14) / 24

// Я даже под пивом теперь не разберусь че здесь происходит.
fun main() {
    task3()
}

fun task3() {
    val dateStart = LocalDateTime(
        year = 2024,
        month = Month.JANUARY,
        dayOfMonth = 1,
        hour = 0,
        minute = 0,
        second = 0
    ).toInstant(TimeZone.currentSystemDefault())

    val lambda = { instant: Instant, weekNum: Int ->
        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        occupancy[dateTime.dayOfWeek]!!.get(dateTime.hour)!!
            .toDouble() / 100 * LAMBDA_MAX * weeks.get((weekNum + 52) % 52 + 1)!! / 100
    }

    val generator = Generator(0x1112F)
    var currentDateTime = dateStart
    for (i in 0..53) {
        val dateEnd = currentDateTime.plus(7, DateTimeUnit.DAY, TimeZone.currentSystemDefault())
        val model = generator.simulateInhomogeneousPoissonProcess(
            fromTime = currentDateTime,
            toTime = dateEnd,
            lambdaMax = LAMBDA_MAX,
            lambda = { instant ->
                lambda(instant, i)
            }
        )
        val weekReport = service(model, i)
        makeReport(weekReport, currentDateTime)

        currentDateTime = dateEnd
    }
}

fun makeReport(weekReport: WeeklyLoadData, dateStart: Instant) {
    // weekReport.validate()
    with(weekReport) {
        println(
            "Итоги ${
                dateStart.toLocalDateTime(TimeZone.currentSystemDefault()).format(LocalDateTime.Format {
                    dayOfMonth()
                    char('-')
                    monthName(MonthNames.ENGLISH_ABBREVIATED)
                    char('-')
                    year()
                })
            }:" +
                    "\nПереработки - ${(overTime * 24 * 60).toInt()} min\n" +
                    "Приходы - ${arrivals} " +
                    "\nУходы - ${departures}"
        )

        var averageTimeClientService = 0.0
        for (i in 0 until weekReport.clientNumber) {
            with(weekReport) {
                averageTimeClientService += departures[i] - arrivals[i]
            }
        }
        averageTimeClientService /= weekReport.clientNumber
        println("Среднее время пребывания клиента в системе - ${(averageTimeClientService * 24 * 60).toInt()} min")

        // не совсем корректно считается (waitTime.size always > clientNumber)
        var averageTimeClientWait = 0.0
        for (i in 0 until weekReport.clientNumber) {
            with(weekReport) {
                averageTimeClientWait += departures[i] - arrivals[i] - waitTime[i]
            }
        }
        averageTimeClientWait /= weekReport.clientNumber
        println("Среднее время ожидания клиента в очереди - ${(averageTimeClientWait * 24 * 60).toInt()} min")

        var i = 0
        var j = 0
        var averageQueueSize = 0.0
        var averageLoad = 0.0
        var n = 0
        var last = 0.0
        // из книжки
        with(weekReport) {
            while (i < clientNumber && j < clientNumber) {
                if (departures[j] > T) {
                    break
                }
                if (arrivals[i] <= departures[j]) {
                    if (n > 1) {
                        averageQueueSize += ((arrivals[i] - last) * (n - 1))
                    }
                    if (n > 0) {
                        averageLoad += (arrivals[i] - last)
                    }
                    last = arrivals[i]
                    i++
                    n++
                    continue
                }
                averageQueueSize += ((departures[j] - last) * (n - 1))
                averageLoad += (departures[i] - last)
                last = departures[j]
                j++
                n--
            }
            averageQueueSize /= T
            averageLoad /= T
            averageLoad /= 2.0
            averageLoad /= 9.1
        }
        println("Среднее число клиентов в очереди - $averageQueueSize")
        println("Средняя занятость устройства - $averageLoad\n")
    }
}

data class WeeklyLoadData(
    val weekNumber: Int,
    val clientNumber: Int,
    val arrivals: List<Double>,
    val departures: List<Double>,
    val waitTime: List<Double>,
    val overTime: Double
) {
    fun validate() {
        if (arrivals.size != clientNumber || departures.size != clientNumber || waitTime.size != clientNumber) {
            throw RuntimeException("Invalid data: ${arrivals.size} ${departures.size} ${waitTime.size}")
        }
    }
}

fun service(arrivals: List<Double>, weekNumber: Int): WeeklyLoadData {
    val generator = Generator(0x1231CF1)
    var inSystem = 0
    var accumulatedTime = 0.0
    var arrivedNumber = 0
    var servicedNumber = 0
    var timeArrival = arrivals[0]
    var timeServiced = Double.MAX_VALUE
    var serviceTime = mutableListOf<Double>()
    var arrivalTime = mutableListOf<Double>()
    var waitTime = mutableListOf<Double>()
    var timeEnd = 0.0
    while (true) {
        if (timeArrival < timeServiced) {
            accumulatedTime = timeArrival
            arrivedNumber++
            if (arrivedNumber < arrivals.size) {
                inSystem++
                timeArrival = arrivals[arrivedNumber]
                val waitFor = generator.generateExponentialValue(SERVICE_LAMBDA)
                waitTime.add(waitFor)
                timeServiced = accumulatedTime + waitFor
                arrivalTime.add(accumulatedTime)
                continue
            }
            timeArrival = Double.MAX_VALUE
            continue
        }
        if (timeServiced < timeArrival && timeServiced <= T) {
            accumulatedTime = timeServiced
            servicedNumber++
            inSystem--
            if (inSystem == 0)
                timeServiced = Double.MAX_VALUE
            else {
                val waitFor = generator.generateExponentialValue(SERVICE_LAMBDA)
                waitTime.add(waitFor)
                timeServiced = accumulatedTime + waitFor
            }
            serviceTime.add(accumulatedTime)
            continue
        }
        if (min(timeArrival, timeServiced) > T && inSystem > 0) {
            accumulatedTime = timeServiced
            servicedNumber++
            inSystem--
            if (inSystem > 0) {
                val waitFor = generator.generateExponentialValue(SERVICE_LAMBDA)
                waitTime.add(waitFor)
                timeServiced = accumulatedTime + waitFor
            }
            serviceTime.add(accumulatedTime)
            continue
        }
        if (min(timeArrival, timeServiced) > T && inSystem == 0) {
            timeEnd = max(accumulatedTime - T, 0.0)
            break
        }
        throw RuntimeException("Unsupervised case")
    }

    return WeeklyLoadData(weekNumber, servicedNumber, arrivalTime, serviceTime, waitTime, timeEnd)
}