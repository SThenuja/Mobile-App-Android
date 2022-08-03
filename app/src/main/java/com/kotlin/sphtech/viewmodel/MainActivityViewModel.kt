package com.kotlin.sphtech.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kotlin.sphtech.model.Record
import com.kotlin.sphtech.network.RetroRepository
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: RetroRepository): ViewModel() {

    fun getAllRecordList(): LiveData<List<Record>>{
        return repository.getAllRecords()
    }
    fun makeApiCall(){
        return repository.makeAPICall("a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
    }


}