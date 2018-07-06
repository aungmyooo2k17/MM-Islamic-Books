package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.category_content_view.view.*
import org.m2cs.mmislamicbooks.activity.CategoryMenu
import org.m2cs.mmislamicbooks.data.model.Category

class CategoryFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    private val tvTitle = itemView.categoryTitle
    private val tvTitle = itemView.categoryTitle
    private val layout: RelativeLayout = itemView.rlLayout
    val context: Context = itemView.context

    private lateinit var mCategory: Category

    fun bind(category: Category) {
        mCategory = category

        tvTitle.text = category.categoryTitle
        layout.setOnClickListener {

            context.startActivity(CategoryMenu.getIntent(context, category))
        }

    }
}

