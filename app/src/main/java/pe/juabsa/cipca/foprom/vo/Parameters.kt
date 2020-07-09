package pe.juabsa.cipca.foprom.vo

import androidx.room.Entity
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import pe.juabsa.cipca.foprom.db.FopromTypeConverters
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(primaryKeys = ["app_codigo", "parameterId", "sub_parameter"])
@TypeConverters(FopromTypeConverters::class)
data class Parameters (
        @field:SerializedName("app_codigo")
        val app_codigo: Int,
        @field:SerializedName("parameterId")
        val parameterId: String,
        @field:SerializedName("sub_parameter")
        val sub_parameter: String,
        @field:SerializedName("description")
        val description: String?,
        @field:SerializedName("short_text")
        val short_text: String,
        @field:SerializedName("long_text")
        val long_text: String,
        @field:SerializedName("integer_value_1")
        val integer_value_1: Double,
        @field:SerializedName("integer_value_2")
        val integer_value_2: Double,
        @field:SerializedName("decimal_value_1")
        val decimal_value_1: Double,
        @field:SerializedName("decimal_value_2")
        val decimal_value_2: Double,
        @field:SerializedName("state")
        val state: Int
)