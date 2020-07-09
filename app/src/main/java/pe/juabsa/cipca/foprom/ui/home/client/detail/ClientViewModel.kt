package pe.juabsa.cipca.foprom.ui.home.client.detail

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.google.android.material.textfield.TextInputLayout
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.repository.ClientRepository
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.ui.common.FormState
import pe.juabsa.cipca.foprom.ui.common.FragmentAdapter
import pe.juabsa.cipca.foprom.util.AbsentLiveData
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Resource
import pe.juabsa.cipca.foprom.vo.User
import java.util.ArrayList
import javax.inject.Inject

@OpenForTesting
class ClientViewModel @Inject constructor(clientRepository: ClientRepository) : ViewModel() {

    private val _clientForm = MutableLiveData<FormState>()
    val clientFormState: LiveData<FormState> = _clientForm

    private val _client = MutableLiveData<Client?>()
    val client: LiveData<Client?>
        get() = _client


    val nextpage = MutableLiveData<Int>()

    val userId = MutableLiveData<Int>()

    var client_data = MutableLiveData<Client>()



    fun fromDataChanged(primer_nombre: TextInputLayout, primer_apellido: TextInputLayout,segundo_apellido: TextInputLayout,
                        dni:TextInputLayout, profesion:TextInputLayout, actividad_principal: TextInputLayout ,
                        celular: TextInputLayout, numero_cuenta: TextInputLayout, direccion: TextInputLayout,
                        referencia: TextInputLayout){
        if(primer_nombre.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = primer_nombre , isEmpty = R.string.error_empty )
        }else if(primer_apellido.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = primer_apellido , isEmpty = R.string.error_empty )
        }else if(segundo_apellido.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = segundo_apellido , isEmpty = R.string.error_empty )
        }else if(dni.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = dni , isEmpty = R.string.error_empty )
        }else if(profesion.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = profesion , isEmpty = R.string.error_empty )
        }else if(actividad_principal.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = actividad_principal , isEmpty = R.string.error_empty )
        }else if(celular.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = celular , isEmpty = R.string.error_empty )
        }else if(numero_cuenta.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = numero_cuenta , isEmpty = R.string.error_empty )
        }else if(direccion.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = direccion , isEmpty = R.string.error_empty )
        }else{
            _clientForm.value = FormState(isDataValid = true)
        }
    }


    fun isValidDocumentForm(textInputLayout: TextInputLayout, error:Int) {
        if(textInputLayout.editText?.text.toString().length<8){
            _clientForm.value = FormState(view = textInputLayout , isEmpty = error )
        }else{
            _clientForm.value = FormState(isDataValid = true)
        }
    }

    fun isEmptyForm(textInputLayout: TextInputLayout, error:Int) {
        var texto = textInputLayout.editText?.text.toString()
        if(textInputLayout.editText?.text.toString().isEmpty()){
            _clientForm.value = FormState(view = textInputLayout , isEmpty = error )
        }else{

            _clientForm.value = FormState(isDataValid = true)
        }
    }




    val LoginUser: LiveData<Resource<Client>> = _client.switchMap { client ->
        if (client == null) {
            AbsentLiveData.create()
        } else {
            clientRepository.saveClient(client)
        }
    }







}