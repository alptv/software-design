package feed.statistics

import java.time.LocalDateTime
import java.time.ZoneOffset


fun LocalDateTime.toUnix() = toEpochSecond(ZoneOffset.UTC)

fun nowUnix() = LocalDateTime.now().toUnix()

fun Long.plusHours(amount: Int) = plus(3600 * amount)

fun Long.plusOneHour() = plusHours(1)

fun Long.minusHours(amount: Int) = plusHours(-amount)
