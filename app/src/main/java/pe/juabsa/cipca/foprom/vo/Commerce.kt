package pe.juabsa.cipca.foprom.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName
import java.math.BigInteger
import java.sql.Time
import java.util.*


@Entity(primaryKeys =  ["commerceId"] , foreignKeys =
arrayOf(
        ForeignKey(
                entity = Client::class,
                parentColumns = ["clientId"],
                childColumns = ["clientId"]
        )
))
data class Commerce(
        @field:SerializedName("commerceId")
        val commerceId: Int?,
        @field:SerializedName("clientId")
        val clientId: Int?,
        @field:SerializedName("name_commerce")
        val commerce_name: String?,
        @field:SerializedName("experience_time")
        val experience_time: Int?,
        @field:SerializedName("business_type")
        val business_type: Int?,
        @field:SerializedName("opening_time")
        val opening_time: String? ,
        @field:SerializedName("closing_time")
        val closing_time: String?,
        @field:SerializedName("type_of_local")
        val type_of_local: Int?,
        @field:SerializedName("rental_payment_frequency")
        val rental_payment_frequency: Int,
        @field:SerializedName("rent_amount")
        val rent_amount: Float,
        @field:SerializedName("ruc")
        val ruc: String,
        @field:SerializedName( "business_days")
        val business_days: String?,
        @field:SerializedName("number_of_male_employees")
        val number_of_male_employeesval : Int,
        @field:SerializedName("number_of_female_employees")
        val number_of_female_employees: Int,
        @field:SerializedName("amount_paid")
        val amount_paid: Float
)