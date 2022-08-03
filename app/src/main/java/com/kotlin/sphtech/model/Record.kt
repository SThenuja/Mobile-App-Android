package com.kotlin.sphtech.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "datastore_search")
data class Record(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") val _id: Int,
    @ColumnInfo(name = "quarter")val quarter: String,
    @ColumnInfo(name = "volume") val volume_of_mobile_data: String


)