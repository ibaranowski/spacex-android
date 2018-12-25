package com.spacex.rockets.presentation.utils

import com.spacex.rockets.data.utils.EMPTY
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


const val PATTERN_COMMON = "yyyy-MM-dd"

fun formatDate(date: Date?, pattern: String): String {

    if (date != null) {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        try {
            return dateFormat.format(date)
        } catch (e: Exception) {
            Timber.e("fromatDate() error", e)
        }
    }

    return EMPTY
}

