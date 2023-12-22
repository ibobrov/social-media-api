package i.bobrov.social.util

import java.sql.ResultSet
import java.time.ZoneId
import java.util.Calendar
import java.util.Date

private val zoneId = ZoneId.systemDefault()

fun ResultSet.getCalendar(columnLabel: String): Calendar =
    Calendar.getInstance().also {
        val localDateTime = getTimestamp(columnLabel).toLocalDateTime()
        it.time = Date.from(localDateTime.atZone(zoneId).toInstant())
    }
