package com.kotlin.sphtech.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.sphtech.R
import com.kotlin.sphtech.activity.DetailActivity
import com.kotlin.sphtech.databinding.DataItemBinding
import com.kotlin.sphtech.model.QuarterData
import com.kotlin.sphtech.model.Record
import com.kotlin.sphtech.model.Summary

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class RecyclerViewAdapter(private val context: Context):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    private var recordList: MutableList<Summary?>?= mutableListOf()
    private val map = HashMap<Int, Double> ()
    private val quarterMap = HashMap<Int, MutableList<QuarterData>>()
    private val delimiter:String = "-"
    fun setListData(list: List<Record?>){
        map.clear()
        recordList!!.clear()


        for(record in list) {
            val year = record!!.quarter.split(delimiter)[0].toInt()
            if(year in 2010..2020) {
                if (!map.containsKey(year)) {
                    val quarterDataList: MutableList<QuarterData> = mutableListOf()
                    quarterDataList.add(
                        QuarterData(
                            year,
                            record.quarter,
                            record.volume_of_mobile_data
                        )
                    )
                    map[year] = record.volume_of_mobile_data.toDouble()
                    quarterMap[year] = quarterDataList
                } else {
                    quarterMap[year]!!
                        .add(QuarterData(year, record.quarter, record.volume_of_mobile_data))
                    map[year] = map.getValue(year) + record.volume_of_mobile_data.toDouble()
                }
            }
        }

        recordList = processData(quarterMap)

    }

    private fun processData(map: HashMap<Int, MutableList<QuarterData>>) : MutableList<Summary?>?{
        val summary :MutableList<Summary?> = mutableListOf()
        val m = map.toSortedMap()
        var count = 0
        m.forEach { (key, value) ->
            val quarterList: MutableList<String> = mutableListOf()
            val volumeList:MutableList<String> = mutableListOf()
            var total: Double= 0.00

            for (v in value){
                quarterList.add(v.quarter)
                total += v.volume.toDouble()
                volumeList.add(v.volume)
            }

            summary.add(Summary(count,key,quarterList,volumeList,total))
            count += 1


        }

        return summary
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent,false)

        return ViewHolder(view).listen { pos ->
            val item = recordList!![pos]
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.SELECTED_YEAR, item)
            intent.putParcelableArrayListExtra(DetailActivity.DETAIL_ACTIVITY_TAG, ArrayList(recordList))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.txtYr.text = recordList!![position]?.year.toString()
            binding.txtTotal.text = recordList!![position]?.total.toString()
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