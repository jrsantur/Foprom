package pe.juabsa.cipca.foprom.vo

import androidx.room.*
import com.google.gson.annotations.SerializedName
import pe.juabsa.cipca.foprom.db.FopromTypeConverters
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Entity(primaryKeys = ["clientId"] , foreignKeys =
                                        arrayOf(
                                                ForeignKey(
                                                    entity = User::class,
                                                    parentColumns = ["userId"],
                                                    childColumns = ["userId"]
                                                )
                                        )
)
@TypeConverters(FopromTypeConverters::class)
data class Client(
        @field:SerializedName("clientId")
        var clientId: Int?,
        @field:SerializedName("userId")
        var userId: Int?,
        @field:SerializedName("name_gs_bc")
        var name_gs_bcval:Int?,
        @field:SerializedName("credit_product")
        var credit_product: Int?,
        @field:SerializedName("document")
        var document: String?,
        @field:SerializedName("first_name")
        var first_name: String?,
        @field:SerializedName("second_name")
        var second_name: String?,
        @field:SerializedName("first_surname ")
        var first_surname: String?,
        @field:SerializedName("second_surname")
        var second_surname: String?,
        @field:SerializedName("sex")
        var sex: String?,
        @field:SerializedName("gender_identity")
        var gender_identity: Int?,
        @field:SerializedName("nickname")
        var nickname: String,
        @field:SerializedName("marital_status")
        var marital_status: Int?,
        @field:SerializedName("birth_date")
        var birth_date: LocalDate?,
        @field:SerializedName("address")
        var address: String?,
        @field:SerializedName("address_reference")
        var address_reference: String?,
        @field:SerializedName("residence_time")
        var residence_time: Int?,
        @field:SerializedName("basic_services")
        var basic_services: String?,
        @field:SerializedName("housing_type")
        var housing_type: Int?,
        @field:SerializedName("mobile")
        var mobile: String,
        @field:SerializedName("profession_occupation")
        var profession_occupation: String?,
        @field:SerializedName("email")
        var email: String,
        @field:SerializedName("main_activity")
        var main_activity:String?,
        @field:SerializedName("secondary_activity")
        var secondary_activity: String,
        @field:SerializedName("number_children")
        var number_children: Int,
        @field:SerializedName("number_daughters")
        var number_daughters: Int,
        @field:SerializedName("number_male_dependents")
        var number_male_dependents: Int?,
        @field:SerializedName("number_female_dependents")
        var number_female_dependents: Int?,
        @field:SerializedName("education_level")
        var education_level: Int?,
        @field:SerializedName("complete_studies")
        var complete_studies: Int?,
        @field:SerializedName("account_number")
        var account_number: String?,
        @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
        var image: ByteArray,
        @Embedded
        @field:SerializedName("job")
        var job: Job?,
        @Embedded
        @field:SerializedName("spouse")
        var spouse: Spouse?
)