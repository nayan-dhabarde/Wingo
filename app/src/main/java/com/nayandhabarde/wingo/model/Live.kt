package com.nayandhabarde.wingo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Live (
        @SerializedName("opens_at")
        val opensAt: String? = null,
        val supported: Boolean = false,
        val url: String? = null
): Parcelable