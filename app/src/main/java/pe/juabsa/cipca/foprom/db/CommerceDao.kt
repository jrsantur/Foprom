package pe.juabsa.cipca.foprom.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Commerce

@Dao
interface CommerceDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(commerce: List<Commerce>)

  @Query("SELECT * FROM commerce WHERE commerce_name =:commerce_name")
  fun findCommerceByName(commerce_name: String): LiveData<List<Commerce>>

  @Query("SELECT * FROM commerce  WHERE clientId =:clienteId")
  fun findCommerceByCliente(clienteId: Int): LiveData<List<Commerce>>


}