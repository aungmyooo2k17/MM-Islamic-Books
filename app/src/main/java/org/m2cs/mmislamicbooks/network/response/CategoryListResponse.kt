package org.m2cs.mmislamicbooks.network.response

import com.google.gson.annotations.SerializedName
import org.m2cs.mmislamicbooks.data.vo.AuthorVO
import org.m2cs.mmislamicbooks.data.vo.CategoryVO

class CategoryListResponse{

    @SerializedName("Sheet1")
    private val mCategoryVOS: List<CategoryVO>? = null
}