//package org.m2cs.mmislamicbooks.data.model
//
//import android.arch.persistence.room.ColumnInfo
//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey
//import android.support.annotation.NonNull
//
//@Entity(tableName = "book_table")
//data class Book(@PrimaryKey
//                 @NonNull
//                 @ColumnInfo(name = "bookId")
//                 var bookId: String,
//                @ColumnInfo(name = "bookName")
//                 var bookName: String,
//                @ColumnInfo(name = "bookSubTitle")
//                 var bookSubTitle: String,
//                @ColumnInfo(name = "bookDesc")
//                 var bookDesc: String,
//                @ColumnInfo(name = "bookCover")
//                 var bookCover: String,
//                @ColumnInfo(name = "bookDetailCover")
//                 var bookDetailCover: String,
//                @ColumnInfo(name = "authorName")
//                 var authorName: String,
//                @ColumnInfo(name = "bookDownloadLink")
//                 var bookDownloadLink: String,
//                @ColumnInfo(name = "categoryId")
//                 var categoryId: String)
////    : Parcelable {
////    constructor(parcel: Parcel) : this(
////            parcel.readString(),
////            parcel.readString(),
////            parcel.readString()) {
////    }
////
////    override fun writeToParcel(parcel: Parcel, flags: Int) {
////        parcel.writeString(bookCover)
////        parcel.writeString(bookName)
////        parcel.writeString(authorName)
////    }
////
////    override fun describeContents(): Int {
////        return 0
////    }
////
////    companion object CREATOR : Parcelable.Creator<Book> {
////        override fun createFromParcel(parcel: Parcel): Book {
////            return Book(parcel)
////        }
////
////        override fun newArray(size: Int): Array<Book?> {
////            return arrayOfNulls(size)
////        }
////    }
////}
