package pe.juabsa.cipca.foprom.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName


@Entity(primaryKeys = ["financialDebtsId"] , foreignKeys =
arrayOf(
        ForeignKey(
                entity = Client::class,
                parentColumns = ["clientId"],
                childColumns = ["clientId"]
        )
)
)
data class FinancialDebts(
    @field:SerializedName("financialDebtsId")
    val financialDebtsId: Int,
    @field:SerializedName("clientId")
    val clientId: Int,
    @field:SerializedName("financial_entity")
    val financial_entity: String,
    @field:SerializedName("type_of_debt")
    val type_of_debt: Int,
    @field:SerializedName("banking_debt")
    val banking_debt: String,
    @field:SerializedName("number_installments")
    val number_installments: Int,
    @field:SerializedName("number_outstanding_installments")
    val number_outstanding_installments: Int,
    @field:SerializedName("payment_frequency")
    val payment_frequency: Int,
    @field:SerializedName("fee_amount")
    val fee_amount: Float

)