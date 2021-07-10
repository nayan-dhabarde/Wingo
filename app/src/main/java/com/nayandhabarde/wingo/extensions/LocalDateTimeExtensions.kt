package com.nayandhabarde.wingo.extensions

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun SimpleDateFormat.formatLongDateToMonthDayOfMonthFormat(dateTimeString: String) {
    val longFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val monthDayOfMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
    val input = longFormat.parse(dateTimeString)
    val output = monthDayOfMonthFormat.format(input)
    return
}