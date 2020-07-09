package pe.juabsa.cipca.foprom.vo

import androidx.room.Embedded
import androidx.room.Relation

data class ClienteDetails (

    @Embedded
    val client :Client,

    @Relation(parentColumn = "propertyId", entityColumn  = "propertyId", entity = Property::class )
    val property: List<Property>,

    @Relation(parentColumn = "financialDebtsId", entityColumn  = "financialDebtsId", entity = FinancialDebts::class )
    val financialDebts: List<FinancialDebts>
)