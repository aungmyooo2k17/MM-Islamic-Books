package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.category_content_view.view.*
import kotlinx.android.synthetic.main.home_content_view.view.*
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.activity.CategoryMenu
import org.m2cs.mmislamicbooks.model.Category

class CategoryFragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//    private val tvTitle = itemView.categoryTitle
    private val tvTitle = itemView.categoryTitle
    private val tvBookCount = itemView.totalBooksCat
    private val imgThumbnail = itemView.categoryImage
    private val layout: RelativeLayout = itemView.rlLayout
    val context: Context = itemView.context

    private lateinit var mCategory: Category

    fun bind(category: Category) {
        mCategory = category

        tvTitle.text = category.categoryTitle
        tvBookCount.text = "Total Books - ${category.totalBooks}"
        imgThumbnail.setImageResource(category.drawableImage)
        layout.setOnClickListener {

            context.startActivity(CategoryMenu.getIntent(context, category))
        }

    }
}

