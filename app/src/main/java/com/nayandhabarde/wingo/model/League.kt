package com.nayandhabarde.wingo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League (
    val id: Long = Long.MIN_VALUE,
    @SerializedName("image_url")
    val imageUrl: String = "",
    @SerializedName("modified_at")
    val modifiedAt: String = "",
    val name: String = "",
    val slug: String = "",
    val url: String = ""
): Parcelable