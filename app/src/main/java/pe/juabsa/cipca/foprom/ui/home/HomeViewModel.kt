package pe.juabsa.cipca.foprom.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.google.android.material.textfield.TextInputLayout
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.repository.ClientRepository
import pe.juabsa.cipca.foprom.repository.UserRepository
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.ui.common.FormState
import pe.juabsa.cipca.foprom.util.AbsentLiveData
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Resource
import javax.inject.Inject

@OpenForTesting
class HomeViewModel @Inject constructor(clientRepository: ClientRepository) : ViewModel() {

  private val _userid: MutableLiveData<Int> = MutableLiveData()
  val userid: LiveData<Int>
    get() = _userid


  val clientResult: LiveData<Resource<List<Client>>> = _userid.switchMap { userid ->
    if (userid == 0 ) {
      AbsentLiveData.create()
    }
    else{
      clientRepository.loadClientsByUser(userid)
    }
  }

  fun setUserid(userId:Int){
    if(_userid.value!= userId){
      _userid.value = userId
    }
  }

}