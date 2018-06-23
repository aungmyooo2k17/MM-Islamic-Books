package org.m2cs.mmislamicbooks.data.vo

import com.google.gson.annotations.SerializedName



class BookVO{
    @SerializedName("book_id")
     var bookId: String = ""

    @SerializedName("book_name")
     var bookName: String = ""

    @SerializedName("book_cover")
     var bookCover: String = ""

    @SerializedName("book_detail_cover")
     var bookDetailCover: String = ""

    @SerializedName("book_sub_title")
     var bookSubTitle: String = ""

    @SerializedName("book_desc")
     var bookDesc: String = ""

    @SerializedName("book_download_link")
     var bookDownloadLink: String = ""

    @SerializedName("author_id")
     var authorId: String = ""

    @SerializedName("category_id")
     var categoryId: String = ""


}