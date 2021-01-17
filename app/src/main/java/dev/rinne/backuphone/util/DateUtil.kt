package dev.rinne.backuphone.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    fun getDate(time: Long): String? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.US)
            val date = Date(time)
            sdf.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
