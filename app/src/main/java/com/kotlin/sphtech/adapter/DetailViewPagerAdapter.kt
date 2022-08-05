package com.kotlin.sphtech.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.sphtech.R
import com.kotlin.sphtech.databinding.UsageDetailsBinding
import com.kotlin.sphtech.model.Summary

class DetailViewPagerAdapter(private val list: MutableList<Summary>
): RecyclerView.Adapter<DetailViewPagerAdapter.DetailViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.usage_details, parent,false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewPagerAdapter.DetailViewHolder, position: Int) {
        val model = list[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class DetailViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = UsageDetailsBinding.bind(view)

        fun bind(model: Summary){
            val str: StringBuilder = StringBuilder()
            val strVolume: StringBuilder = StringBuilder()
            binding.txtYear.text = model!!.year.toString()

            for (qr in model.quarterList)
                str.append(qr+"\n")

            for (v in model.volumeList){
                strVolume.append(v+"\n")
            }

            binding.txtQuarter.text = str.toString()
            binding.txtVolume.text = strVolume.toString()
        }
    }
}