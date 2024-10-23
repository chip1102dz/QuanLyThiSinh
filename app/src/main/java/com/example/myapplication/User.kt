package com.example.myapplication

import android.os.Parcel
import android.os.Parcelable

data class User(
    var id: Int = 0,
    var name: String = "",
    var toan: Int = 0,
    var hoa: Int = 0,
    var ly: Int = 0,
    var uutien: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(toan)
        parcel.writeInt(hoa)
        parcel.writeInt(ly)
        parcel.writeString(uutien)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}