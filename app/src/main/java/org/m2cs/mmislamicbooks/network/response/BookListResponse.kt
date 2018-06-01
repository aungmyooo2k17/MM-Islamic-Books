package org.m2cs.mmislamicbooks.network.response

import com.google.gson.annotations.SerializedName
import org.m2cs.mmislamicbooks.data.vo.BookVO


class BookListResponse{

    @SerializedName("Sheet1")
    private val mBooKVOS: List<BookVO>? = null

}