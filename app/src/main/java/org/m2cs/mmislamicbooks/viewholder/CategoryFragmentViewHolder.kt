package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_item_category_content.view.*
import org.m2cs.mmislamicbooks.activity.CategoryDetailActivity
import org.m2cs.mmislamicbooks.data.model.Category
import org.m2cs.mmislamicbooks.data.vo.CategoryVO

class CategoryFragmentViewHolder(itemView: View) : BaseViewHolder<CategoryVO>(itemView) {


    private val tvTitle = itemView.categoryTitle
    private val layout: RelativeLayout = itemView.rlLayout
    val context: Context = itemView.context

    private lateinit var mCategory: Category


    override fun bind(data: CategoryVO) {
        tvTitle.text = data.categoryType
        itemView.setOnClickListener(View.OnClickListener {
            Log.d("TAG", "catBind"+data.categoryId)
            context.startActivity(CategoryDetailActivity.getIntent(context, data.categoryId+"", data.categoryType+""))
        })
    }

}

