package org.m2cs.mmislamicbooks.data.model


data class Category(var  totalBooks:Int, var categoryTitle:String)
//    :Parcelable {
//    constructor(parcel: Parcel) : this(
//            parcel.readInt(),
//            parcel.readInt(),
//            parcel.readString()) {
//    }
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeInt(drawableImage)
//        parcel.writeInt(totalBooks)
//        parcel.writeString(categoryTitle)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Category> {
//        override fun createFromParcel(parcel: Parcel): Category {
//            return Category(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Category?> {
//            return arrayOfNulls(size)
//        }
//    }
//}