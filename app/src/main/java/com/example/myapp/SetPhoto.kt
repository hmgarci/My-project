package com.example.myapp

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SetPhoto(val url: String): Parcelable {
   // constructor(parcel: Parcel) : this(parcel.readString())
    companion object {
        fun getSunsetPhotos(): Array<SetPhoto> {
            return arrayOf(
                //SetPhoto("https://goo.gl/32YN2B"),
                SetPhoto("https://firebasestorage.googleapis.com/v0/b/login-c3c2a.appspot.com/o/images%2F106611050?alt=media&token=50f046fb-0b61-4359-b0d5-ce3bd1a2ec92"),
                SetPhoto("https://goo.gl/Wqz4Ev"),
                SetPhoto("https://goo.gl/U7XXdF"),
                SetPhoto("https://goo.gl/ghVPFq"),
                SetPhoto("https://goo.gl/qEaCWe"),
                SetPhoto("https://goo.gl/vutGmM")
            )
        }
    }
}