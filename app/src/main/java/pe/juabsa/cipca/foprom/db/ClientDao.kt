package pe.juabsa.cipca.foprom.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.User

@Dao
interface ClientDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(client: List<Client>)

  @Query("SELECT * FROM client   WHERE userId =:userId")
  fun findClientByUser(userId: Int): LiveData<List<Client>>
}