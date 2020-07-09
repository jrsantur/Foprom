package pe.juabsa.cipca.foprom.ui.home.solicitude

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.ui.home.HomeViewModel
import javax.inject.Inject

class SolicitudeFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }
}
