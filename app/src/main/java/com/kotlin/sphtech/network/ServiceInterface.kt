package com.kotlin.sphtech.network

import com.kotlin.sphtech.model.MobileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceInterface {

    @GET("datastore_search")
    fun getDataFromAPI(@Query("resource_id") query:String):Call<MobileData>
}