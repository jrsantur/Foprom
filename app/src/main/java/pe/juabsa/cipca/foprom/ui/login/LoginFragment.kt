package pe.juabsa.cipca.foprom.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.juabsa.cipca.foprom.*

import pe.juabsa.cipca.foprom.binding.FragmentDataBindingComponent
import pe.juabsa.cipca.foprom.databinding.LoginFragmentBinding
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.util.SharedPrefsHelper
import pe.juabsa.cipca.foprom.util.autoCleared
import pe.juabsa.cipca.foprom.vo.Parameters
import pe.juabsa.cipca.foprom.vo.Resource
import pe.juabsa.cipca.foprom.vo.Status
import pe.juabsa.cipca.foprom.vo.User
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoCleared<LoginFragmentBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private lateinit var loginResult: Resource<User>

    private val loginViewModel: LoginViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.login_fragment,
                container,
                false,
                dataBindingComponent
        )


        if (SharedPrefsHelper.get<List<Parameters>>("KEY_PRODUCTO_CREDITICIO")==null ) {
            loginViewModel.setParameter("PRODUCTO_CREDITICIO")
            loginViewModel.parms.observe(viewLifecycleOwner, Observer {
                var result = it ?: return@Observer
                SharedPrefsHelper.put(result.data , "KEY_PRODUCTO_CREDITICIO")
            })
        }


        if (SharedPrefsHelper.get<List<Parameters>>("KEY_GS_BC")==null ) {
            loginViewModel.setParameter1("GS_BC")
            loginViewModel.parms1
            loginViewModel.parms1.observe(viewLifecycleOwner, Observer {
                var result = it ?: return@Observer
                result.data?.forEach { parm ->
                    SharedPrefsHelper.put(parm, "KEY_" + parm.parameterId + "_" + parm.sub_parameter)
                }
            })
        }

        if (SharedPrefsHelper.get<List<Parameters>>("KEY_SEXO_BIOLOGICO")==null ) {
            loginViewModel.setParameter2("SEXO_BIOLOGICO")
            loginViewModel.parms2
            loginViewModel.parms2.observe(viewLifecycleOwner, Observer {
                var result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_IDENTIDAD_GENERO")==null ) {
            loginViewModel.setParameter3("IDENTIDAD_GENERO")
            loginViewModel.parms3
            loginViewModel.parms3.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_ESTADO_CIVIL")==null ) {
            loginViewModel.setParameter4("ESTADO_CIVIL")
            loginViewModel.parms4
            loginViewModel.parms4.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_NIVEL_EDUCACIÓN")==null ) {
            loginViewModel.setParameter5("NIVEL_EDUCACIÓN")
            loginViewModel.parms5
            loginViewModel.parms5.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_CONCLUYO_ESTUDIOS")==null ) {
            loginViewModel.setParameter6("CONCLUYO_ESTUDIOS")
            loginViewModel.parms6
            loginViewModel.parms6.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_EDUCACION")==null ) {
            loginViewModel.setParameter7("TIPO_EDUCACION")
            loginViewModel.parms7
            loginViewModel.parms7.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_CASA")==null ) {
            loginViewModel.setParameter8("TIPO_CASA")
            loginViewModel.parms8
            loginViewModel.parms8.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_TRABAJO")==null ) {
            loginViewModel.setParameter9("TIPO_TRABAJO")
            loginViewModel.parms9
            loginViewModel.parms9.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_FRECUENCIA_SALARIO")==null ) {
            loginViewModel.setParameter10("FRECUENCIA_SALARIO")
            loginViewModel.parms10
            loginViewModel.parms10.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_EMPRESA")==null ) {
            loginViewModel.setParameter11("TIPO_EMPRESA")
            loginViewModel.parms11
            loginViewModel.parms11.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_NEGOCIO")==null ) {
            loginViewModel.setParameter12("TIPO_NEGOCIO")
            loginViewModel.parms12
            loginViewModel.parms12.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_TIPO_LOCAL_NEGOCIO")==null ) {
            loginViewModel.setParameter13("TIPO_LOCAL_NEGOCIO")
            loginViewModel.parms13
            loginViewModel.parms13.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_PAGO_ALQUILER")==null ) {
            loginViewModel.setParameter14("PAGO_ALQUILER")
            loginViewModel.parms14
            loginViewModel.parms14.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }
        if (SharedPrefsHelper.get<List<Parameters>>("KEY_PERIODICIDAD_VENTAS")==null ) {
            loginViewModel.setParameter15("PERIODICIDAD_VENTAS")
            loginViewModel.parms15
            loginViewModel.parms15.observe(viewLifecycleOwner, Observer {
                val result = it ?: return@Observer
                result.data?.forEach {
                    parm -> SharedPrefsHelper.put(parm, "KEY_"+parm.parameterId+"_"+parm.sub_parameter)
                }
            })
        }



        // Make sure we don't wait longer than a second for the image request
        postponeEnterTransition(1, TimeUnit.SECONDS)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.username.afterTextChanged {
            loginViewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
            )
        }


        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            binding.btnlogin.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })


        binding.password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        binding.username.text.toString(),
                        binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {

                }
                false
            }

            binding.btnlogin.setOnClickListener {
                binding.loading.visibility = View.VISIBLE
                loginViewModel.setLogin(binding.username.text.toString())
                loginViewModel.setPassword(binding.password.text.toString())
                loginViewModel.LoginUser
            }
        }

        loginViewModel.LoginUser.observe(viewLifecycleOwner , Observer {
            val loginResult = it ?: return@Observer
            if (loginResult.data!=null ) {
                processResult(loginResult!!)
            }
        })
    }

    private fun processResult(result: Resource<User>?) {

        if (result !=null){
            if (result.status == Status.ERROR ) {
                showLoginFailed(this.loginResult.message)
            }

            if (result.status == Status.LOADING){
                binding.loading.visibility = View.VISIBLE
            }
            if (result.status == Status.SUCCESS){
                binding.loading.visibility = View.GONE
                val pass = binding.password.text.toString()

                if (pass.equals(result.data?.password)) {
                    showHome(result.data)
                }
            }
            binding.loading.visibility = View.GONE

        }else{
            Toast.makeText(activity, "Error de inicio de sesión", Toast.LENGTH_LONG).show()
        }
    }

    private fun showLoginFailed(errorString: String?) {
        Toast.makeText(activity, errorString, Toast.LENGTH_LONG).show()
    }

    private fun showHome(user: User?) {

        SharedPrefsHelper.put(user , "KEY_USER")
        //obtenemos parametros de las tablas

        /*

        */
        binding.loading.visibility = View.GONE
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
