package pe.juabsa.cipca.foprom.db

import androidx.room.TypeConverter
import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class  FopromTypeConverters {

    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toTime(dateString: String?): LocalTime? {
        return if (dateString == null) {
            null
        } else {
            val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME

            LocalTime.parse(dateString, formatter)
        }
    }

    @TypeConverter
    fun toTimeString(date: LocalTime?): String? {
        return date?.toString()
    }


    @TypeConverter
    fun stringToIntList(data: String?): List<Int>? {
        return data?.let {
            it.split(",").map {
                try {
                    if(!it.equals("")){
                        it.toInt()
                    }else{
                        0
                    }
                } catch (ex: NumberFormatException) {
                    Timber.e(ex, "Cannot convert $it to number")
                    null
                }
            }
        }?.filterNotNull()
    }

    @TypeConverter
    fun intListToString(ints: List<Int>?): String? {
        return ints?.joinToString(",")
    }


}
