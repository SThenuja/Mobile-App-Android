package com.kotlin.sphtech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summary( val _id:Int,
                 val year:Int,
                 val quarterList: MutableList<String>,
                 val volumeList: MutableList<String>,
                 val total: Double): Parcelable
