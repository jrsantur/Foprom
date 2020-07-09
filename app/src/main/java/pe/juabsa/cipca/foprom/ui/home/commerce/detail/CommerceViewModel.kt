package pe.juabsa.cipca.foprom.ui.home.commerce.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.juabsa.cipca.foprom.repository.ClientRepository
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.vo.Client
import javax.inject.Inject

@OpenForTesting
class CommerceViewModel @Inject constructor(clientRepository: ClientRepository) : ViewModel()  {

    val nextpage = MutableLiveData<Int>()

    val userId = MutableLiveData<Int>()

    var client_data = MutableLiveData<Client>()

}