package com.kotlin.sphtech.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class QuarterData(val year:Int,
    val quarter: String,
    val volume:String): Parcelable {

}
