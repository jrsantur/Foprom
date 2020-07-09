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


class InventoryInformationFragment : Fragment() , Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    val clientViewModel: ClientViewModel by viewModels {
        viewModelFactory
    }

    private val ARG_PAGE = "page"
    private val ARG_ISLAST = "isLast"
    private var position = 3

    private var page: Int? = null
    private var isLast: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            page = it.getInt(ARG_PAGE)
            isLast = it.getBoolean(ARG_ISLAST)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_inventory_information, container, false)

        val btnNext = rootView.findViewById<Button>(R.id.btnNext)
        val btnBack = rootView.findViewById<Button>(R.id.btnBack)
        val addInventory = rootView.findViewById<Button>(R.id.btnAddProperty)

        btnNext.setOnClickListener {
            clientViewModel.nextpage.postValue(position+1)
        }
        btnBack.visibility = View.VISIBLE
        btnBack.setOnClickListener {
            clientViewModel.nextpage.postValue(position-1)
        }
        addInventory.setOnClickListener {
            val fm = activity!!.supportFragmentManager.beginTransaction()
            val myFragment = DialogInventoryFragment()
            if (fm != null) {
                myFragment.show(fm, "Bienes del negocio")
            }
        }


        return  rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(page: Int, isLast: Boolean) =
                InventoryInformationFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PAGE, page)
                        putBoolean(ARG_ISLAST, isLast)
                    }
                }
    }
}
