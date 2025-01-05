package com.resep.mymenu_resep_kue

import android.os.Parcel
import android.os.Parcelable

data class Kue(
    val name: String,
    val description: String,
    val photo: Int,
    val kategori: String,
    val detail: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(photo)
        parcel.writeString(kategori)
        parcel.writeString(detail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kue> {
        override fun createFromParcel(parcel: Parcel): Kue {
            return Kue(parcel)
        }

        override fun newArray(size: Int): Array<Kue?> {
            return arrayOfNulls(size)
        }
    }
}
