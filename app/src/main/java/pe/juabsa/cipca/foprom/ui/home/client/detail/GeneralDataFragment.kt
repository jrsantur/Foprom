package pe.juabsa.cipca.foprom.ui.home.client.detail

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.btn_layout.view.*
import kotlinx.android.synthetic.main.fragment_general_data.*
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.binding.FragmentDataBindingComponent
import pe.juabsa.cipca.foprom.databinding.FragmentGeneralDataBinding
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.ui.common.DatePickerFragment
import pe.juabsa.cipca.foprom.ui.custom.MultiSelectionSpinner
import pe.juabsa.cipca.foprom.util.SharedPrefsHelper
import pe.juabsa.cipca.foprom.util.autoCleared
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Parameters
import pe.juabsa.cipca.foprom.vo.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


class GeneralDataFragment : Fragment(), Injectable, MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoCleared<FragmentGeneralDataBinding>()
    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    val clientViewModel: ClientViewModel by viewModels {
        viewModelFactory
    }
    var cal = Calendar.getInstance()
    var valid = false
    var position = 0

    var indexServices = ""

    fun newInstance(page: Int, isLast: Boolean): GeneralDataFragment? {
        val args = Bundle()
        args.putInt("page", page)
        position = page
        if (isLast) args.putBoolean("isLast", true)
        val fragment = GeneralDataFragment()
        fragment.setArguments(args)
        return fragment
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


            binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_general_data,
                    container,
                    false,
                    dataBindingComponent
            )

            binding.content5.btnNext.isEnabled = false

            val sexList  = arrayListOf<String>(
                    SharedPrefsHelper.get<Parameters>("KEY_SEXO_BIOLOGICO_HOMBRE")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_SEXO_BIOLOGICO_INTERSEXUAL")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_SEXO_BIOLOGICO_MUJER")?.description!!
            )

            var adapter: ArrayAdapter<String> = ArrayAdapter(
                    requireActivity().applicationContext,
                    R.layout.dropdown_menu_popup_item,
                    sexList)
            binding.listSex = sexList
            binding.spinnerSex.setAdapter(adapter)


            val generes = arrayListOf<String>(
                    SharedPrefsHelper.get<Parameters>("KEY_IDENTIDAD_GENERO_CISGENERO")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_IDENTIDAD_GENERO_QUIER")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_IDENTIDAD_GENERO_TANSGÉNERO")?.description!!
            )

            adapter = ArrayAdapter(
                    requireActivity().applicationContext,
                    R.layout.dropdown_menu_popup_item,
                generes)
            binding.listGenderIdentity = generes
            binding.spinnerGenderIdentity.setAdapter(adapter)

            val state_civil = arrayListOf<String>(
                    SharedPrefsHelper.get<Parameters>("KEY_ESTADO_CIVIL_SOLTERA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_ESTADO_CIVIL_CASADA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_ESTADO_CIVIL_VIUDA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_ESTADO_CIVIL_DIVORCIADA")?.description!!
            )
            adapter = ArrayAdapter(
                    requireActivity().applicationContext,
                    R.layout.dropdown_menu_popup_item,
                    state_civil)
            binding.spinnerCivilStatus.setAdapter(adapter)


            val nivel = arrayListOf<String>(
                    SharedPrefsHelper.get<Parameters>("KEY_NIVEL_EDUCACIÓN_SIN_INSTRUCCIÓ")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_NIVEL_EDUCACIÓN_PRIMARIA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_NIVEL_EDUCACIÓN_SUPERRIOS_TÉCNICA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_NIVEL_EDUCACIÓN_SUPERTIOR_UNIVERSITARIA")?.description!!
            )
            adapter = ArrayAdapter(
                    requireActivity().applicationContext,
                    R.layout.dropdown_menu_popup_item,
                    nivel)
            binding.spinnerEducationLevel.setAdapter(adapter)

            val house = arrayListOf<String>(
                    SharedPrefsHelper.get<Parameters>("KEY_TIPO_CASA_PROPIA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_TIPO_CASA_ALQUILADA")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_TIPO_CASA_FAMILIAR")?.description!!,
                    SharedPrefsHelper.get<Parameters>("KEY_TIPO_CASA_OTRO")?.description!!
            )
            adapter = ArrayAdapter(
                    requireActivity().applicationContext,
                    R.layout.dropdown_menu_popup_item,
                    house)
            binding.spinnerHouse.setAdapter(adapter)


            val array = resources.getStringArray(R.array.arr_basic_services)
            val mySpinner = binding.searchMultiSpinnerUnlimited as MultiSelectionSpinner
            mySpinner.setItems(array)
            mySpinner.setListener(this)


            binding.firstNameClient.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                                                binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                                                binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.firstLastNameClient.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.secondLastNameClient.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.documeClient.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.professionClient.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.mainActivity.afterTextChanged{
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }

            binding.numCellphone.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.numAccount.afterTextChanged{
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }

            binding.addressClient.afterTextChanged{
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }
            binding.homeReference.afterTextChanged {
                clientViewModel.fromDataChanged(binding.inFirstNameClient, binding.inFirstLastNameClient,binding.inSecondLastNameClient,
                        binding.inDocumeClient,binding.inProfessionClient,binding.inMainActivity,binding.inNumCellphone,
                        binding.inNumAccount, binding.inAddressClient, binding.inHomeReference)
            }





            binding.birthdateClient.setOnClickListener {
                showDatePickerDialog()
            }



            if (binding.birthdateClient.text!!.isBlank()){
                clientViewModel.isEmptyForm(binding.inBirthdateClient, R.string.error_empty )
            }



            binding.spinnerGenderIdentity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    clientViewModel.isEmptyForm(binding.inSpinnerGenderIdentity, R.string.error_empty)
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                }

            }

            clientViewModel.clientFormState.observe(viewLifecycleOwner, Observer {
                val formState = it ?: return@Observer
                binding.content5.btnNext.isEnabled  = formState.isDataValid
                if (formState.view !=null){
                    formState.view.editText?.error = getString(formState.isEmpty!!)
                }
            })



            //CODIGO DE USUARIO
            val user = SharedPrefsHelper.get<User>("KEY_USER")


            binding.content5.btnNext.setOnClickListener {

                val data = Client(
                        0,
                        user?.userId,
                        0,
                        0,
                        in_docume_client.editText!!.text.toString(),
                        in_first_name_client.editText!!.text.toString(),
                        in_second_name_client.editText!!.text.toString(),
                        in_first_last_name_client.editText!!.text.toString(),
                        in_second_last_name_client.editText!!.text.toString(),
                        in_spinner_sex.editText!!.text.toString(),
                        generes.indexOf(in_spinner_gender_identity.editText!!.text.toString()),
                        in_alias_client.editText!!.text.toString(),
                        state_civil.indexOf(in_spinner_civil_status.editText!!.text.toString()),
                        LocalDate.parse(in_birthdate_client.editText!!.text.toString(), DateTimeFormatter.ISO_DATE),
                        in_address_client.editText!!.text.toString(),
                        in_home_reference.editText!!.text.toString(),
                        in_residence_time.editText!!.text.toString().toInt(),
                        "0",
                        house.indexOf(in_spinner_house.editText!!.text.toString()),

                        in_num_cellphone.editText!!.text.toString(),
                        in_profession_client.editText!!.text.toString(),
                        in_email_client.editText!!.text.toString(),
                        in_main_activity.editText!!.text.toString(),
                        in_secondary_activity.editText!!.text.toString(),
                        in_num_son.editText!!.text.toString().toInt(),
                        in_num_daughter.editText!!.text.toString().toInt(),
                        in_num_depende.editText!!.text.toString().toInt(),
                        0,
                        nivel.indexOf(in_spinner_education_level.editText!!.text.toString()),
                        0,
                        in_num_account.editText!!.text.toString(),
                        ByteArray(1),
                        null,
                        null
                )

                clientViewModel.nextpage.postValue(position+1)
                clientViewModel.client_data.postValue(data)
            }

            return binding.root
    }


    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }


    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            var days : String = ""
            var months : String= ""
            var month1 = month
            if(day<10){
                days = "0$day"
            }
            if (month<10){
                month1 += 1
                months = "0$month1"
            }

            val selectedDate = days + "/" + months + "/" + year
            binding.inBirthdateClient.editText!!.setText(selectedDate)
        })
        newFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    override fun selectedIndices(indices: MutableList<Int?>?) {
        indices?.forEach {
            index -> indexServices = indexServices+index
        }
    }

    override fun selectedStrings(strings: MutableList<String?>?) {
        Toast.makeText(requireActivity(), strings.toString(), Toast.LENGTH_LONG).show()

    }



}