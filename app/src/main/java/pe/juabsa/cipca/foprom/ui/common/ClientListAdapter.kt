package pe.juabsa.cipca.foprom.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.databinding.ItemClientBinding
import pe.juabsa.cipca.foprom.databinding.RepoItemBinding
import pe.juabsa.cipca.foprom.vo.Client
import pe.juabsa.cipca.foprom.vo.Repo

class ClientListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val clientClickCallback: ((Client) -> Unit)?
        ): DataBoundListAdapter<Client, ItemClientBinding> (
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Client>() {

            override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
                return oldItem.image == newItem.image
                        //&& oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
                return oldItem.first_name == newItem.first_name
                        //&& oldItem.name == newItem.name
            }

        }
        )

{
    override fun createBinding(parent: ViewGroup): ItemClientBinding {
        val binding = DataBindingUtil.inflate<ItemClientBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_client,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.client?.let {
                clientClickCallback?.invoke(it)
            }
        }
        return binding
    }
    override fun bind(binding: ItemClientBinding, item: Client) {
        binding.client = item
    }
}

