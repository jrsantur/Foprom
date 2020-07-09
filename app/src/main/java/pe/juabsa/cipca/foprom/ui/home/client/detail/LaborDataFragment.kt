package pe.juabsa.cipca.foprom.ui.home.client.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_labor_data.*
import pe.juabsa.cipca.foprom.AppExecutors


import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.vo.Job
import javax.inject.Inject

class LaborDataFragment : Fragment() , Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    val clientViewModel: ClientViewModel by viewModels {
        viewModelFactory
    }

    var position = 1


    fun newInstance(page: Int, isLast: Boolean): LaborDataFragment? {
        val args = Bundle()
        args.putInt("page", page)
        if (isLast) args.putBoolean("isLast", true)
        val fragment = LaborDataFragment()
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_labor_data, container, false)

        val btnNext = rootView.findViewById<Button>(R.id.btnNext)
        val btnBack = rootView.findViewById<Button>(R.id.btnBack)

        btnNext.setOnClickListener {
            clientViewModel.nextpage.postValue(position+1)
        }
        btnBack.visibility = View.VISIBLE
        btnBack.setOnClickListener {
            clientViewModel.nextpage.postValue(position-1)
            clientViewModel.client_data.observe(viewLifecycleOwner, Observer {
                val client = it
                val job = Job(
                        in_company_work.editText!!.text.toString(),
                        in_address_bussiness.editText!!.text.toString(),
                        in_job_possition.editText!!.text.toString(),
                        in_time_work.editText!!.text.toString().toInt(),
                        in_spinner_type_work.editText!!.text.toString().toInt(),
                        in_spinner_salary_frequency_client.editText!!.text.toString().toInt(),
                        in_salary.editText!!.text.toString().toFloat(),
                        in_salary_month.editText!!.text.toString().toFloat()
                )
                client.job = job
                clientViewModel.client_data.postValue(client)
            })

        }

        return rootView
    }
}
