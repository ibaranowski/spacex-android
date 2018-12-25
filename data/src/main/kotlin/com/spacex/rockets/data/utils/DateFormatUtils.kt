package com.spacex.rockets.data.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMATS_FULL = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private val DATE_FORMATS_SHORT = "yyyy-MM-dd"

fun deserializeDate(dateString: String): Date? {

    for ((count, format) in listOf(DATE_FORMATS_FULL, DATE_FORMATS_SHORT).withIndex()) {
        try {
            val sd = SimpleDateFormat(format, Locale.US)
            if (count == 0) sd.timeZone = TimeZone.getTimeZone("GMT")
            return sd.parse(dateString)
        } catch (e: ParseException) {
        }
    }

    return null
}

fun serializeShortDate(date: Date?): String? {

    try {
        val sd = SimpleDateFormat(DATE_FORMATS_SHORT, Locale.US)
        return sd.format(date)
    } catch (e: ParseException) {
    }

    return null
}