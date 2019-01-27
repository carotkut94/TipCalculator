package com.death.tip.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.death.tip.R
import com.death.tip.databinding.SingleItemBinding
import com.death.tip.viewmodel.TipCalculationSummaryItem

class TipSummaryAdapter(val onItemSelected : (item: TipCalculationSummaryItem) -> Unit) : RecyclerView.Adapter<TipSummaryAdapter.TipViewHolder>() {


    private val tipCalculationSummaryItem = mutableListOf<TipCalculationSummaryItem>()

    fun updateList(updates : List<TipCalculationSummaryItem>){
        tipCalculationSummaryItem.clear()
        tipCalculationSummaryItem.addAll(updates)
        notifyDataSetChanged()
    }

    inner class TipViewHolder(val binding: SingleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TipCalculationSummaryItem){
            binding.item = item
            binding.root.setOnClickListener{
                onItemSelected(item)
            }
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryAdapter.TipViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val  binding = DataBindingUtil.inflate<SingleItemBinding>(inflater, R.layout.single_item, parent, false)
        return TipViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Log.e("SIZE","$tipCalculationSummaryItem.size")
        return tipCalculationSummaryItem.size
    }

    override fun onBindViewHolder(holder: TipSummaryAdapter.TipViewHolder, position: Int) {
        holder.bind(tipCalculationSummaryItem[position])
    }
}