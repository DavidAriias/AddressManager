package david.arias.addressmanager.config.helpers


import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateHelper {

    private val inputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    private val outputFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd")


    fun toEpochMillis(date: String): Long {
        return runCatching {
            LocalDateTime.parse(date.trim(), inputFormatter)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()
        }.getOrDefault(0L)
    }


    fun formatEpochToDate(millis: Long): String {
        return runCatching {
            Instant.ofEpochMilli(millis)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(outputFormatter)
        }.getOrDefault("Desconocido")
    }
}