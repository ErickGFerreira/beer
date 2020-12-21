package com.egferreira.pagtest.base.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ErrorResponse(
    @SerializedName("type")
    @Expose
    val type: String = "",

    @SerializedName("title")
    @Expose
    val title: String = "",

    @SerializedName("status")
    @Expose
    val status: String = "",

    @SerializedName("detail")
    @Expose
    val detail: String = "",

    @SerializedName("message")
    @Expose
    val message: String = ""
) : Parcelable
