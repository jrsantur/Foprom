package pe.juabsa.cipca.foprom.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.repository.ParameterRepository
import pe.juabsa.cipca.foprom.repository.UserRepository
import pe.juabsa.cipca.foprom.testing.OpenForTesting
import pe.juabsa.cipca.foprom.util.AbsentLiveData
import pe.juabsa.cipca.foprom.vo.Parameters
import pe.juabsa.cipca.foprom.vo.Resource
import pe.juabsa.cipca.foprom.vo.User
import javax.inject.Inject

@OpenForTesting
class LoginViewModel @Inject constructor(userRepository: UserRepository, parametersRepository: ParameterRepository) : ViewModel() {

  private val _loginForm = MutableLiveData<LoginFormState>()
  val loginFormState: LiveData<LoginFormState> = _loginForm

  private val _login = MutableLiveData<String?>()
  val login: LiveData<String?>
    get() = _login

  private val _parmeter = MutableLiveData<String?>()
  val parameter: LiveData<String?>
    get() = _parmeter

  private val _parmeter1 = MutableLiveData<String?>()
  val parameter1: LiveData<String?>
    get() = _parmeter1

  private val _parmeter2 = MutableLiveData<String?>()
  val parameter2: LiveData<String?>
    get() = _parmeter2

  private val _parmeter3 = MutableLiveData<String?>()
  val parameter3: LiveData<String?>
    get() = _parmeter3

  private val _parmeter4 = MutableLiveData<String?>()
  val parameter4: LiveData<String?>
    get() = _parmeter4

  private val _parmeter5 = MutableLiveData<String?>()
  val parameter5: LiveData<String?>
    get() = _parmeter5

  private val _parmeter6 = MutableLiveData<String?>()
  val parameter6: LiveData<String?>
    get() = _parmeter6

  private val _parmeter7 = MutableLiveData<String?>()
  val parameter7: LiveData<String?>
    get() = _parmeter7

  private val _parmeter8 = MutableLiveData<String?>()
  val parameter8: LiveData<String?>
    get() = _parmeter8

  private val _parmeter9 = MutableLiveData<String?>()
  val parameter9: LiveData<String?>
    get() = _parmeter9

  private val _parmeter10 = MutableLiveData<String?>()
  val parameter10: LiveData<String?>
    get() = _parmeter10

  private val _parmeter11 = MutableLiveData<String?>()
  val parameter11: LiveData<String?>
    get() = _parmeter11

  private val _parmeter12 = MutableLiveData<String?>()
  val parameter12: LiveData<String?>
    get() = _parmeter12

  private val _parmeter13 = MutableLiveData<String?>()
  val parameter13: LiveData<String?>
    get() = _parmeter13

  private val _parmeter14 = MutableLiveData<String?>()
  val parameter14: LiveData<String?>
    get() = _parmeter14

  private val _parmeter15 = MutableLiveData<String?>()
  val parameter15: LiveData<String?>
    get() = _parmeter15

  private val _parmeter16 = MutableLiveData<String?>()
  val parameter16: LiveData<String?>
    get() = _parmeter16

  private val _password = MutableLiveData<String?>()
  val password: LiveData<String?>
    get() = _password

  val LoginUser: LiveData<Resource<User>> = _login.switchMap { login ->
    if (login == null) {
      AbsentLiveData.create()
    } else {
      userRepository.loadUser(login)
    }
  }


  val parms: LiveData<Resource<List<Parameters>>> = _parmeter.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms1: LiveData<Resource<List<Parameters>>> = _parmeter1.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms2: LiveData<Resource<List<Parameters>>> = _parmeter2.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms3: LiveData<Resource<List<Parameters>>> = _parmeter3.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms4: LiveData<Resource<List<Parameters>>> = _parmeter4.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms5: LiveData<Resource<List<Parameters>>> = _parmeter5.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms6: LiveData<Resource<List<Parameters>>> = _parmeter6.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms7: LiveData<Resource<List<Parameters>>> = _parmeter7.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms8: LiveData<Resource<List<Parameters>>> = _parmeter8.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms9: LiveData<Resource<List<Parameters>>> = _parmeter9.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms10: LiveData<Resource<List<Parameters>>> = _parmeter10.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms11: LiveData<Resource<List<Parameters>>> = _parmeter11.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms12: LiveData<Resource<List<Parameters>>> = _parmeter12.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms13: LiveData<Resource<List<Parameters>>> = _parmeter13.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms14: LiveData<Resource<List<Parameters>>> = _parmeter14.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }
  val parms15: LiveData<Resource<List<Parameters>>> = _parmeter15.switchMap { parme ->
    if(parme==null){
      AbsentLiveData.create()
    }else{
      parametersRepository.loadParameter(parme)
    }
  }

  fun setParameter(par: String?){
    if(_parmeter.value != par){
      _parmeter.value = par
    }
  }

  fun setParameter1(par: String?){
    if(_parmeter1.value != par){
      _parmeter1.value = par
    }
  }

  fun setParameter2(par: String?){
    if(_parmeter2.value != par){
      _parmeter2.value = par
    }
  }

  fun setParameter3(par: String?){
    if(_parmeter3.value != par){
      _parmeter3.value = par
    }
  }

  fun setParameter4(par: String?){
    if(_parmeter4.value != par){
      _parmeter4.value = par
    }
  }

  fun setParameter5(par: String?){
    if(_parmeter5.value != par){
      _parmeter5.value = par
    }
  }

  fun setParameter6(par: String?){
    if(_parmeter6.value != par){
      _parmeter6.value = par
    }
  }

  fun setParameter7(par: String?){
    if(_parmeter7.value != par){
      _parmeter7.value = par
    }
  }

  fun setParameter8(par: String?){
    if(_parmeter8.value != par){
      _parmeter8.value = par
    }
  }

  fun setParameter9(par: String?){
    if(_parmeter9.value != par){
      _parmeter9.value = par
    }
  }

  fun setParameter10(par: String?){
    if(_parmeter10.value != par){
      _parmeter10.value = par
    }
  }

  fun setParameter11(par: String?){
    if(_parmeter11.value != par){
      _parmeter11.value = par
    }
  }
  fun setParameter12(par: String?){
    if(_parmeter12.value != par){
      _parmeter12.value = par
    }
  }
  fun setParameter13(par: String?){
    if(_parmeter13.value != par){
      _parmeter13.value = par
    }
  }
  fun setParameter14(par: String?){
    if(_parmeter14.value != par){
      _parmeter14.value = par
    }
  }

  fun setParameter15(par: String?){
    if(_parmeter15.value != par){
      _parmeter15.value = par
    }
  }

  fun setParameter16(par: String?){
    if(_parmeter16.value != par){
      _parmeter16.value = par
    }
  }


  fun setLogin(login: String?) {
    if (_login.value != login) {
      _login.value = login
    }
  }

  fun setPassword(password: String?){
    if(_password.value != password) {
      _password.value = password
    }
  }

  fun retry() {
    _login.value?.let {
      _login.value = it
    }
  }

  fun loginDataChanged(username: String, password: String) {
    if (!isUserNameValid(username)) {
      _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
    } else if (!isPasswordValid(password)) {
      _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
    } else {
      _loginForm.value = LoginFormState(isDataValid = true)
    }
  }

  // A placeholder username validation check
  private fun isUserNameValid(username: String): Boolean {
    return if (username.contains('@')) {
      Patterns.EMAIL_ADDRESS.matcher(username).matches()
    } else {
      username.isNotBlank()
    }
  }

  // A placeholder password validation check
  private fun isPasswordValid(password: String): Boolean {
    return password.length > 5
  }

}
