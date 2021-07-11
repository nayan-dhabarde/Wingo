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

    fun transformStartAndEndDateToMonthDateOfMonthRange(startDateString: String, endDateString: String?): String {
        val monthDayOfMonthFormat = SimpleDateFormat("MMM d", Locale.getDefault())
        val startDate = serverDateFormat.parse(startDateString)
        var endText = "NA"
        if(endDateString != null) {
            val endDate = serverDateFormat.parse(endDateString)
            endText = monthDayOfMonthFormat.format(endDate)
        }
        return "${monthDayOfMonthFormat.format(startDate)} - ${endText}"
    }

    fun getCurrentMonthDateServerFormatted(): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return serverDateFormat.format(calendar.timeInMillis)
    }

    fun getCurrentMonthDatePlusNextYearServerFormatted(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, 1)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return serverDateFormat.format(calendar.timeInMillis)
    }
}