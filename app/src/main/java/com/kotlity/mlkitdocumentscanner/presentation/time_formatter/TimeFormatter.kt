package com.kotlity.mlkitdocumentscanner.presentation.time_formatter

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.formatTime(pattern: String = "yyyy_MM_dd_HH:mm:ss", locale: Locale = Locale.getDefault()): String {
    val simpleDateFormat = SimpleDateFormat(pattern, locale)
    val date = Date(this)
    return simpleDateFormat.format(date)
}