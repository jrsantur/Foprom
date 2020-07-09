package pe.juabsa.cipca.foprom.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import pe.juabsa.cipca.foprom.AppExecutors
import pe.juabsa.cipca.foprom.R
import pe.juabsa.cipca.foprom.databinding.ItemCommerceBinding
import pe.juabsa.cipca.foprom.vo.Commerce

class CommerceListAdapter(
        private val dataBindingComponent: DataBindingComponent,
        appExecutors: AppExecutors,
        private val commerceClickCallback: ((Commerce) -> Unit)?
): DataBoundListAdapter<Commerce, ItemCommerceBinding> (
        appExecutors = appExecutors,
        diffCallback = object : DiffUtil.ItemCallback<Commerce>() {

            override fun areItemsTheSame(oldItem: Commerce, newItem: Commerce): Boolean {
                return oldItem.commerce_name == newItem.commerce_name
                //&& oldItem.name == newItem.name
            }
            override fun areContentsTheSame(oldItem: Commerce, newItem: Commerce): Boolean {
                return oldItem.commerce_name == newItem.commerce_name
                //&& oldItem.name == newItem.name
            }

        }
)

{
    override fun createBinding(parent: ViewGroup): ItemCommerceBinding {
        val binding = DataBindingUtil.inflate<ItemCommerceBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_commerce,
                parent,
                false,
                dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.commerce?.let {
                commerceClickCallback?.invoke(it)
            }
        }
        return binding
    }
    override fun bind(binding: ItemCommerceBinding, item: Commerce) {
        binding.commerce = item
    }
}


