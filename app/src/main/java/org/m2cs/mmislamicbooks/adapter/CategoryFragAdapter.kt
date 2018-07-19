package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.CategoryVO
import org.m2cs.mmislamicbooks.viewholder.CategoryFragmentViewHolder

class CategoryFragAdapter(val context: Context?) : BaseRecyclerAdapter<CategoryFragmentViewHolder, CategoryVO>(context!!) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryFragmentViewHolder {
        val view = mLayoutInflater.inflate(R.layout.view_item_category_content, parent, false)

        return CategoryFragmentViewHolder(view)
    }


    override fun onBindViewHolder(holder: CategoryFragmentViewHolder, position: Int) {

        holder.bind(mData!!.get(position))
    }

}