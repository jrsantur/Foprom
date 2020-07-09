package pe.juabsa.cipca.foprom.vo

import androidx.room.Entity
import androidx.room.ForeignKey
import com.google.gson.annotations.SerializedName


@Entity(primaryKeys = ["propertyId"] , foreignKeys =
arrayOf(
        ForeignKey(
                entity = Client::class,
                parentColumns = ["clientId"],
                childColumns = ["clientId"]
        )
)
)
data class Property (
        @field:SerializedName("propertyId")
        val propertyId: Int,
        @field:SerializedName("clientId")
        val clientId: Int,
        @field:SerializedName("kind_of_property")
        val kind_of_property: Int,
        @field:SerializedName("property_description")
        val property_description: Int,
        @field:SerializedName("product_unit")
        val product_unit: Int,
        @field:SerializedName("stock")
        val stock: Float,
        @field:SerializedName("sales_price")
        val sales_price: Float,
        @field:SerializedName("purchase_price")
        val purchase_price: Float
)