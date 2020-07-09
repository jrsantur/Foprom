package pe.juabsa.cipca.foprom.ui.home.commerce.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders

import pe.juabsa.cipca.foprom.R

private const val ARG_PAGE = "page"
private const val ARG_ISLAST = "isLast"

class GeneralBusinessDataFragment : Fragment() {
     // TODO: Rename and change types of parameters
     private var page: Int? = 0
     private var isLast: Boolean? = false
     var position = 0
     private lateinit var viewModel: SharedViewModel

     override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          arguments?.let {
               page = it.getInt(ARG_PAGE)
               isLast = it.getBoolean(ARG_ISLAST)
          }
          viewModel = activity?.run {
               ViewModelProviders.of(this)[SharedViewModel::class.java]
          } ?: throw Exception("Invalid Activity")

     }

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
          // Inflate the layout for this fragment
          val rootView =  inflater.inflate(R.layout.fragment_general_business_data, container, false)

          val btnNext = rootView.findViewById<Button>(R.id.btnNext)
          val btnBack = rootView.findViewById<Button>(R.id.btnBack)

          btnNext.setOnClickListener {
               viewModel.nextpage.postValue(position+1)
          }

          return rootView
     }

     companion object {
          /**
           * Use this factory method to create a new instance of
           * this fragment using the provided parameters.
           *
           * @param param1 Parameter 1.
           * @param param2 Parameter 2.
           * @return A new instance of fragment GeneralBusinessDataFragment.
           */
          // TODO: Rename and change types and number of parameters
          @JvmStatic
          fun newInstance(page: Int, isLast: Boolean) =
                  GeneralBusinessDataFragment().apply {
                       arguments = Bundle().apply {
                            putInt(ARG_PAGE, page)
                            putBoolean(ARG_ISLAST, isLast)
                       }
                  }
     }
}
