package com.bgralop.truthordare.presentation.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bgralop.truthordare.databinding.ItemNhieBinding
import com.bgralop.truthordare.model.NeverHaveIEverQuestion

class NeverHaveIEverAdapter: RecyclerView.Adapter<NeverHaveIEverAdapter.NeverHaveIEverAdapterListViewHolder>() {

    var neverHaveIEverList: List<NeverHaveIEverQuestion> = emptyList()

    var onClickListener: (NeverHaveIEverQuestion) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeverHaveIEverAdapterListViewHolder {
        val binding = ItemNhieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NeverHaveIEverAdapterListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return neverHaveIEverList.size
    }

    override fun onBindViewHolder(holder: NeverHaveIEverAdapterListViewHolder, position: Int) {
        val item = neverHaveIEverList[position]

        holder.rootView.setOnClickListener {
            onClickListener.invoke(item)
        }

        holder.nameTextView.text = item.question
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<NeverHaveIEverQuestion>) {
        neverHaveIEverList = list
        notifyDataSetChanged()
    }


    inner class NeverHaveIEverAdapterListViewHolder(binding: ItemNhieBinding): RecyclerView.ViewHolder(binding.root) {
        val rootView = binding.root
        val nameTextView = binding.tvItemNhie
    }

}