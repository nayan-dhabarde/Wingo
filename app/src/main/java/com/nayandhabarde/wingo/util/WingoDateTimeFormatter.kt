package com.nayandhabarde.wingo.util

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@Singleton
class WingoDateTimeFormatter @Inject constructor(
    private val serverDateFormat: SimpleDateFormat
) {

    fun formatToMonthDateOfMonth(dateString: String): String {
        val monthDayOfMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val input = serverDateFormat.parse(dateString)
        return monthDayOfMonthFormat.format(input)
    }
}