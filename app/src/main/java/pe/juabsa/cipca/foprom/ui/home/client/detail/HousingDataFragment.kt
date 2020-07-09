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

class HousingDataFragment : Fragment(),  Injectable {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject
  lateinit var appExecutors: AppExecutors


  val clientViewModel: ClientViewModel by viewModels {
    viewModelFactory
  }


  var position = 1

  fun newInstance(page: Int, isLast: Boolean): HousingDataFragment? {
    val args = Bundle()
    args.putInt("page", page)
    if (isLast) args.putBoolean("isLast", true)
    val fragment = HousingDataFragment()
    fragment.setArguments(args)
    return fragment
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    val rootView =  inflater.inflate(R.layout.fragment_housing_data, container, false)

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

  companion object {

    @JvmStatic
    fun newInstance(param1: String, param2: String) =
            HousingDataFragment().apply {
              arguments = Bundle().apply {

              }
            }
  }
}
