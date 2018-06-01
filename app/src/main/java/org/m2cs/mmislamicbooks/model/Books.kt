package org.m2cs.mmislamicbooks.model

import android.os.Parcel
import android.os.Parcelable

data class Books(var imageLink:String="https://api.androidhive.info/json/movies/lady_bird.jpg",
                 var bookName:String="Lady Bird",
                 var authorName:String="Greta Gerwig"): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageLink)
        parcel.writeString(bookName)
        parcel.writeString(authorName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Books> {
        override fun createFromParcel(parcel: Parcel): Books {
            return Books(parcel)
        }

        override fun newArray(size: Int): Array<Books?> {
            return arrayOfNulls(size)
        }
    }
}
