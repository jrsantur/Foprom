package pe.juabsa.cipca.foprom.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pe.juabsa.cipca.foprom.vo.Parameters

@Dao
interface ParameterDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(parameters: List<Parameters>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(parameter: Parameters)

  @Query("select * from parameters where parameterId =:parameter and trim(sub_parameter) <> ''"  )
  fun findByParameter(parameter:String): LiveData<List<Parameters>>

  @Query("select * from parameters where parameterId=:parameter and sub_parameter=:sub_parameter")
  fun findBySubparameter(parameter:String, sub_parameter:String) : LiveData<Parameters>

}