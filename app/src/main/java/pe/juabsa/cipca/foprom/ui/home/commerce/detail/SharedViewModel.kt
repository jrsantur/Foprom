package pe.juabsa.cipca.foprom.ui.home.commerce.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.juabsa.cipca.foprom.vo.Client

class SharedViewModel : ViewModel() {

    val nextpage = MutableLiveData<Int>()

    val userId = MutableLiveData<Int>()

    var client_data = MutableLiveData<Client>()
}