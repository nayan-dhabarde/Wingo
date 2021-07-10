package com.nayandhabarde.wingo.util

import com.nayandhabarde.wingo.di.ServerDateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@Singleton
class WingoDateTimeFormatter @Inject constructor(
    @ServerDateFormat
    private val serverDateFormat: SimpleDateFormat
) {

    fun formatToMonthDateOfMonth(dateString: String): String {
        val monthDayOfMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val input = serverDateFormat.parse(dateString)
        return monthDayOfMonthFormat.format(input)
    }

    fun transformStartAndEndDateToMonthDateOfMonthRange(startDateString: String, endDateString: String): String {
        val monthDayOfMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val startDate = serverDateFormat.parse(startDateString)
        val endDate = serverDateFormat.parse(endDateString)
        return "${monthDayOfMonthFormat.format(startDate)} - ${monthDayOfMonthFormat.format(endDate)}"
    }

    fun getCurrentMonthDateServerFormatted(): String {
        return serverDateFormat.format(Date())
    }
}