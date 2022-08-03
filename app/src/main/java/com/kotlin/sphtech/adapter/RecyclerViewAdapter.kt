package com.kotlin.sphtech.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.sphtech.R
import com.kotlin.sphtech.activity.DetailActivity
import com.kotlin.sphtech.databinding.DataItemBinding
import com.kotlin.sphtech.model.AnnualUsage
import com.kotlin.sphtech.model.Record

class RecyclerViewAdapter(private val context: Context):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private var recordList: MutableList<AnnualUsage?>?= mutableListOf()
    private val map = HashMap<Int, Double> ()
    //private val quarterMap = HashMap<Int, List<>>
    private val delimiter:String = "-"
    fun setListData(list: List<Record?>){
        list.groupBy {
            Log.e("Adapter", "Year.... ${it!!.quarter == "2004-Q3"}")
        }
        map.clear()
        recordList!!.clear()
        for(record in list) {
            map[record!!.quarter.split(delimiter)[0].toInt()] = if (map.containsKey(record.quarter.split(delimiter)[0].toInt()))
                                map[record.quarter.split(delimiter)[0].toInt()]!! + record.volume_of_mobile_data.toDouble()
                                else record.volume_of_mobile_data.toDouble()
        }

        for(key in map.keys){
            recordList!!.add(AnnualUsage(key,map.getValue(key)))
            recordList!!.sortBy { it!!.year }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent,false)

        //return ViewHolder(view)
        return ViewHolder(view).listen { pos ->
            val item = recordList!![pos]
            val intent = Intent(context, DetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            Log.e("Adapter", "Onclick... ${item!!.year}")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.txtYr.text = recordList!![position]?.year.toString()
            binding.txtTotal.text = recordList!![position]?.usage.toString()
        }
    }

    override fun getItemCount(): Int {
        if(recordList == null) return 0
        return recordList!!.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = DataItemBinding.bind(view)
    }

    private fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition)
        }
        return this
    }

}