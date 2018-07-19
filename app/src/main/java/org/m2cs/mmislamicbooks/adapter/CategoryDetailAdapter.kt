package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.CategoryDetailViewHolder
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class CategoryDetailAdapter(context: Context, val mBooksItemDelegate: BooksItemDelegate): BaseRecyclerAdapter<CategoryDetailViewHolder, BookVO>(context){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryDetailViewHolder {
        val view = mLayoutInflater.inflate(R.layout.view_item_book_list_item, parent, false)

        return CategoryDetailViewHolder(view, mBooksItemDelegate)
    }

    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }


}