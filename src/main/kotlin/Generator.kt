package com.vervyle

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlin.math.log
import kotlin.random.Random
import kotlin.time.DurationUnit

class Generator(
    seed: Int = DEFAULT_SEED,
) {
    private var random = object : Random() {
        var baseRandom = Random(seed)

        override fun nextBits(bitCount: Int): Int {
            return baseRandom.nextBits(bitCount)
        }

        override fun nextDouble(): Double {
            val double = baseRandom.nextDouble()
//            print(double)
            return double
        }
    }

    fun setSeed(seed: Int) {
        random.baseRandom = Random(seed)
    }

    fun generateExponentialValue(lambda: Double) =
        -1.0 * log(random.nextDouble(), Math.E) / lambda

    fun simulateInhomogeneousPoissonProcess(
        fromTime: Instant,
        toTime: Instant,
        lambdaMax: Double,
        lambda: (Instant) -> Double,
    ): List<Double> {
        if (toTime < fromTime)
            return emptyList()
        var accumulatedTime = 0.0
        val totalTime = (toTime - fromTime).toDouble(DurationUnit.DAYS)
        val list = mutableListOf<Double>()
        // generating next HPP event time
        while (accumulatedTime < totalTime) {
            val nextEventTime = generateExponentialValue(lambdaMax)
            accumulatedTime += nextEventTime
            // thinning method
            if (accumulatedTime < totalTime) {
                val lambdaTime = lambda(
                    fromTime.plus(
                        (accumulatedTime * 24 * 60 * 60).toLong(),
                        DateTimeUnit.SECOND,
                        TimeZone.currentSystemDefault()
                    )
                )
                val uniformVal = random.nextDouble()

                if (uniformVal <= lambdaTime / lambdaMax) {
                    list.add(accumulatedTime)
                }
            }
        }
        return list
    }

    companion object {
        const val DEFAULT_SEED = 0
        const val DEFAULT_LAMBDA = 10.0
    }
}