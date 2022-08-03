package com.kotlin.sphtech.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.sphtech.model.Record

@Dao
interface AppDao {

    @Query("SELECT * FROM datastore_search")
    fun getAllRecords():LiveData<List<Record>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insetRecords(record: Record)

    @Query("DELETE FROM datastore_search")
    fun deleteAllRecords()


}