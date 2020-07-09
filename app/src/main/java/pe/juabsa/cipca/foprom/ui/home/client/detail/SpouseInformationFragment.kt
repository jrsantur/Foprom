package pe.juabsa.cipca.foprom.ui.home.client.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import pe.juabsa.cipca.foprom.AppExecutors


import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.di.Injectable
import javax.inject.Inject

class SpouseInformationFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors


    val clientViewModel: ClientViewModel by viewModels {
        viewModelFactory
    }

    var position = 2

    fun newInstance(page: Int, isLast: Boolean): SpouseInformationFragment? {
        val args = Bundle()
        args.putInt("page", page)
        if (isLast) args.putBoolean("isLast", true)
        val fragment = SpouseInformationFragment()
        fragment.setArguments(args)
        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.spouse_information_fragment, container, false)

        val btnNext = rootView.findViewById<Button>(R.id.btnNext)
        val btnBack = rootView.findViewById<Button>(R.id.btnBack)



        btnNext.setOnClickListener {
            clientViewModel.nextpage.postValue(position+1)
        }
        btnBack.visibility = View.VISIBLE
        btnBack.setOnClickListener {
            clientViewModel.nextpage.postValue(position-1)
        }

        return rootView
    }

}
