package com.kotlin.sphtech.network

import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.LiveData
import com.kotlin.sphtech.db.AppDao
import com.kotlin.sphtech.model.MobileData
import com.kotlin.sphtech.model.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetroRepository @Inject constructor(private val serviceInterface: ServiceInterface,
                                            private val appDao: AppDao) {
    private val TAG :String = "RetroRepository"
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler

    fun getAllRecords(): LiveData<List<Record>> {
        return appDao.getAllRecords()
    }

    fun insertRecord(record: Record){
        appDao.insetRecords(record)
    }

    fun makeAPICall(query: String?){
        val call: Call<MobileData> = serviceInterface.getDataFromAPI(query!!)
        Log.e(TAG, "Request: ${call.request()}")
        /*var recordList: List<Record>? = ArrayList()

        handlerThread = HandlerThread("Repository")
        handlerThread.start()
        handler = Handler(handlerThread.looper)

        handler.post {


        }*/

        call.enqueue(object : Callback<MobileData>{
            override fun onResponse(call: Call<MobileData>, response: Response<MobileData>) {
                if(response.isSuccessful){
                    Log.i(TAG, "Successfully get data: ${response.body()?.result?.records}")
                    response.body()?.result?.records!!.forEach{
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<MobileData>, t: Throwable) {
                Log.e(TAG, "Unable to get data: ${t.message}")
            }

        })

    }
}