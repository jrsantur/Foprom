package pe.juabsa.cipca.foprom.ui.home.client

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.ClientDetailActivity
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.binding.FragmentDataBindingComponent
import pe.juabsa.cipca.foprom.databinding.FragmentClientBinding
import pe.juabsa.cipca.foprom.di.Injectable
import pe.juabsa.cipca.foprom.ui.common.ClientListAdapter
import pe.juabsa.cipca.foprom.ui.home.HomeViewModel
import pe.juabsa.cipca.foprom.util.autoCleared
import javax.inject.Inject

class ClientFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    var binding by autoCleared<FragmentClientBinding>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private val params by navArgs<ClientFragmentArgs>()


    var adapter by autoCleared<ClientListAdapter>()

    override fun onCreateView(
            inflater: LayoutInflater,container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val dataBinding = DataBindingUtil.inflate<FragmentClientBinding>(
                inflater,
                R.layout.fragment_client,
                container,
                false
        )
        /*
        dataBinding.retryCallback = object : RetryCallback {
            override fun retry() {
                clientViewModel.retry()
            }
        }
        */

        binding = dataBinding
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(R.transition.move)

        binding.fabClient.setOnClickListener {
            val intent = Intent(activity?.applicationContext, ClientDetailActivity::class.java)
            startActivity(intent)
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.setUserid(1)
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = ClientListAdapter(dataBindingComponent, appExecutors){
            client ->
            //findNavController().navigate(ClientFragmentDirections.showClienteDetailFragment(client.userId.toString(), client.cientId.toString()))
            val intent = Intent(activity?.applicationContext, ClientDetailActivity::class.java)
            startActivity(intent)
        }
        this.adapter = adapter
        binding.clientList.adapter = adapter
        postponeEnterTransition()
        binding.clientList.doOnPreDraw {
            startPostponedEnterTransition()
        }
        initClientList(homeViewModel)

    }

    //enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.client, menu);
        menu!!.findItem(R.id.action_done).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }


    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun initClientList(viewModel: HomeViewModel) {
        homeViewModel.clientResult.observe(viewLifecycleOwner, Observer { client ->
            if (client?.data != null) {
                adapter.submitList(client.data)
            } else {
                adapter.submitList(emptyList())
            }
        })
    }

}
