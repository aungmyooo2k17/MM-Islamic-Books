package org.m2cs.mmislamicbooks.network.response

import com.google.gson.annotations.SerializedName
import org.m2cs.mmislamicbooks.data.vo.AuthorVO
import org.m2cs.mmislamicbooks.data.vo.BookVO

class AuthorListResponse{

    @SerializedName("Sheet1")
    private val mAuthorVOS: List<AuthorVO>? = null

}