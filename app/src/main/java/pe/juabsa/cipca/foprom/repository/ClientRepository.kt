package pe.juabsa.cipca.foprom.repository

import androidx.lifecycle.LiveData
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.api.ApiResponse
import pe.juabsa.cipca.foprom.api.FopromService
import pe.juabsa.cipca.foprom.db.ClientDao
import pe.juabsa.cipca.foprom.db.UserDao
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Resource
import pe.juabsa.cipca.foprom.vo.User
import javax.inject.Inject
import javax.inject.Singleton

@OpenForTesting
@Singleton
class ClientRepository @Inject constructor(
        private val appExecutors: AppExecutors,
        private val clientDao: ClientDao,
        private val fopromService: FopromService
) {

  fun loadClientsByUser(userid: Int): LiveData<Resource<List<Client>>> {
    return object : NetworkBoundResource<List<Client>, List<Client>>(appExecutors) {
      override fun saveCallResult(item: List<Client>) {
        clientDao.insert(item)
      }

      override fun shouldFetch(data: List<Client>?) = data == null

      override fun loadFromDb() = clientDao.findClientByUser(userid)

      override fun createCall() = fopromService.getClientByUser(userid)

    }.asLiveData()
  }

  fun saveClient(client: Client): LiveData<Resource<Client>>{


    return object :NetworkBoundResource<Client, Client>(appExecutors){
      override fun saveCallResult(item: Client) {

      }

      override fun shouldFetch(data: Client?): Boolean {
        TODO("Not yet implemented")
      }

      override fun loadFromDb(): LiveData<Client> {
        TODO("Not yet implemented")
      }

      override fun createCall(): LiveData<ApiResponse<Client>> {
        TODO("Not yet implemented")
      }

    }.asLiveData()
  }



}