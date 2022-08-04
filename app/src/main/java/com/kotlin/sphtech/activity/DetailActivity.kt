package com.kotlin.sphtech.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.kotlin.sphtech.adapter.DetailViewPagerAdapter
import com.kotlin.sphtech.databinding.ActivityDetailBinding
import com.kotlin.sphtech.model.Summary

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var myAdapter:DetailViewPagerAdapter
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getParcelableArrayListExtra<Summary>(DETAIL_ACTIVITY_TAG) as MutableList<Summary>
        val summary = intent.getParcelableExtra<Summary>(SELECTED_YEAR)

        viewPager2 = binding.viewPager

        myAdapter = DetailViewPagerAdapter(data)
        viewPager2.adapter = myAdapter
        viewPager2.setCurrentItem(summary!!._id,false)
    }

    companion object{
        val DETAIL_ACTIVITY_TAG = "quarterData"
        val SELECTED_YEAR = "Year"
    }

}