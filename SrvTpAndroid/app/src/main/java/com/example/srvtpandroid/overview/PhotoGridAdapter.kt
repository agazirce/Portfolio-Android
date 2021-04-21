package com.example.srvtpandroid.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.srvtpandroid.databinding.GridViewItemBinding
import com.example.srvtpandroid.network.OrderProperty

class PhotoGridAdapter(private val onClickListener: OnClickListener) : ListAdapter<OrderProperty, PhotoGridAdapter.OrderPropertyViewHolder>(DiffCallback) {
    class OrderPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(orderProperty: OrderProperty) {
            binding.property = orderProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<OrderProperty>() {
        override fun areItemsTheSame(oldItem: OrderProperty, newItem: OrderProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: OrderProperty, newItem: OrderProperty): Boolean {
            return oldItem.price == newItem.price
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderPropertyViewHolder {
        return OrderPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: OrderPropertyViewHolder, position: Int) {
        val marsProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(marsProperty)
        }
        holder.bind(marsProperty)
    }

    class OnClickListener(val clickListener: (orderProperty:OrderProperty) -> Unit) {
        fun onClick(orderProperty: OrderProperty) = clickListener(orderProperty)
    }

}
