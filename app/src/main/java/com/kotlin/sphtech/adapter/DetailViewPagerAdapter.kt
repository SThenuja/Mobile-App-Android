package com.kotlin.sphtech.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.kotlin.sphtech.R
import com.kotlin.sphtech.activity.DetailActivity
import com.kotlin.sphtech.databinding.DataItemBinding
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

        /*viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)

                when (state) {
                    ViewPager2.SCROLL_STATE_DRAGGING -> {
                        holder.toolbar.navigationIcon = null
                        holder.fab.visibility = View.INVISIBLE
                    }
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        holder.toolbar.setNavigationIcon(R.drawable.ic_up_button)
                        holder.fab.visibility = View.VISIBLE
                    }
                    ViewPager2.SCROLL_STATE_SETTLING -> {
                        holder.toolbar.navigationIcon = null
                        holder.fab.visibility = View.INVISIBLE
                    }
                }
            }
        })*/
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