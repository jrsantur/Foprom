package pe.juabsa.cipca.foprom.ui.home.commerce

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingComponent
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_commerce.view.*
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.CommerceDetailActivity
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.binding.FragmentDataBindingComponent
import pe.juabsa.cipca.foprom.databinding.FragmentCommerceBinding
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.ui.home.HomeViewModel
import pe.juabsa.cipca.foprom.util.autoCleared
import javax.inject.Inject

class CommerceFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoCleared<FragmentCommerceBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val viewroot = inflater.inflate(R.layout.fragment_commerce, container, false)

        viewroot.fab_commerce.setOnClickListener {
            val intent = Intent(activity?.applicationContext, CommerceDetailActivity::class.java)
            startActivity(intent)
        }
        return viewroot
    }
}
