package org.m2cs.mmislamicbooks.data.vo

import com.google.gson.annotations.SerializedName



class BookVO{
    @SerializedName("book_id")
    public var bookId: String? = null

    @SerializedName("book_name")
    public var bookName: String? = null

    @SerializedName("book_cover")
    public var bookCover: String? = null

    @SerializedName("book_detail_cover")
    public var bookDetailCover: String? = null

    @SerializedName("book_sub_title")
    public var bookSubTitle: String? = null

    @SerializedName("book_desc")
    public var bookDesc: String? = null

    @SerializedName("book_download_link")
    public var bookDownloadLink: String? = null

    @SerializedName("author_id")
    public var authorId: String? = null

    @SerializedName("category_id")
    public var categoryId: String? = null


}