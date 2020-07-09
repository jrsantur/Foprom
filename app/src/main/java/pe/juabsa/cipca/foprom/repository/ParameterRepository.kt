package pe.juabsa.cipca.foprom.repository

import androidx.lifecycle.LiveData
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.api.ApiResponse
import pe.juabsa.cipca.foprom.api.FopromService
import pe.juabsa.cipca.foprom.db.ParameterDao
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.vo.Parameters
import pe.juabsa.cipca.foprom.vo.Resource
import java.lang.reflect.Parameter
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
class ParameterRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val parameterDao: ParameterDao,
        private val fopromService: FopromService
) {


    fun loadParameter(parameter:String): LiveData<Resource<List<Parameters>>> {
        return object : NetworkBoundResource<List<Parameters>, List<Parameters>>(appExecutors) {
            override fun saveCallResult(item: List<Parameters>) {
                parameterDao.insert(item)
            }

            override fun shouldFetch(data: List<Parameters>?) = data == null

            override fun loadFromDb() = parameterDao.findByParameter(parameter)

            override fun createCall() = fopromService.getParameter(parameter)


        }.asLiveData()
    }

    fun loadSubParameter(parameter:String, subparameters: String): LiveData<Resource<Parameters>> {
        return object : NetworkBoundResource<Parameters, Parameters>(appExecutors) {
            override fun saveCallResult(item: Parameters) {
                parameterDao.insert(item)
            }

            override fun shouldFetch(data: Parameters?) = data == null

            override fun loadFromDb() = parameterDao.findBySubparameter(parameter,subparameters)

            override fun createCall() = fopromService.getSubParameter(parameter, subparameters)


        }.asLiveData()
    }



}