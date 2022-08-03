package com.kotlin.sphtech.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kotlin.sphtech.R
import com.kotlin.sphtech.model.Record

class ViewPagerAdapter(private val context: Context, private val recordList: List<Record>) :PagerAdapter() {
    override fun getCount(): Int {
        return  recordList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.usage_details,container,false)
        recordList.groupBy { it.quarter }
        return super.instantiateItem(container, position)
    }
}