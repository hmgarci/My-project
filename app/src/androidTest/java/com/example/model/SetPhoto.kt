package com.example.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetPhoto(val url: String): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString())
    companion object {
        fun getSunsetPhotos(): Array<SetPhoto> {
            return arrayOf<SetPhoto>(SetPhoto("https://goo.gl/32YN2B"),
                SetPhoto("https://goo.gl/Wqz4Ev"),
                SetPhoto("https://goo.gl/U7XXdF"),
                SetPhoto("https://goo.gl/ghVPFq"),
                SetPhoto("https://goo.gl/qEaCWe"),
                SetPhoto("https://goo.gl/vutGmM"))
        }
    }
}