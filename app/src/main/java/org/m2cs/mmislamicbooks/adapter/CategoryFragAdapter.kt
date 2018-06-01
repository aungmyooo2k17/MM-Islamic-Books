package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.model.Books
import org.m2cs.mmislamicbooks.model.Category
import org.m2cs.mmislamicbooks.viewholder.CategoryFragmentViewHolder
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class CategoryFragAdapter( val context: Context?): RecyclerView.Adapter<CategoryFragmentViewHolder>() {

    val mCategoryList: ArrayList<Category> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryFragmentViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_content_view, parent, false)
        return CategoryFragmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    override fun onBindViewHolder(holder: CategoryFragmentViewHolder, position: Int) {

        holder.bind(mCategoryList[position])
    }

    fun replaceData(catList: List<Category>) {
        mCategoryList.clear()
        mCategoryList.addAll(catList)
        notifyDataSetChanged()
    }
}