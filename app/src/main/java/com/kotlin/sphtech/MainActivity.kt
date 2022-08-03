package com.kotlin.sphtech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.sphtech.adapter.RecyclerViewAdapter
import com.kotlin.sphtech.databinding.ActivityMainBinding
import com.kotlin.sphtech.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initViewModelAdapter()
    }

    private fun initRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)

            recyclerViewAdapter = RecyclerViewAdapter(applicationContext)
            adapter = recyclerViewAdapter
        }
    }

    private fun initViewModelAdapter(){
        val viewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRecordList().observe(this, Observer {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        viewModel.makeApiCall()

    }

}