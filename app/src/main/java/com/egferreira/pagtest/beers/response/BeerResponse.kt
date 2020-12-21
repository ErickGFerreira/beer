package com.egferreira.pagtest.beers.response

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BeerResponse(
    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("abv")
    @Expose
    val abv: Float,

    @Expose
    @SerializedName("tagline")
    val tagline: String,

    @Expose
    @SerializedName("ibu")
    val ibu: Float,

    @Expose
    @SerializedName("description")
    val description: String
) : Parcelable