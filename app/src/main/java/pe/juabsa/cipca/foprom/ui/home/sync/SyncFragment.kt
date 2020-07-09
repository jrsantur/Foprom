package pe.juabsa.cipca.foprom.ui.home.sync

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import pe.juabsa.cipca.foprom.R

class SyncFragment : Fragment() {

    companion object {
        fun newInstance() = SyncFragment()
    }

    private lateinit var viewModel: SyncViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.sync_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SyncViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
