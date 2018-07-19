package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class HomeFragAdapter(val context: Context?, val mBooksItemDelegate: BooksItemDelegate) : BaseRecyclerAdapter<HomeFragViewHolder, BookVO>(context!!) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragViewHolder {
        val view = mLayoutInflater.inflate(R.layout.view_item_home_content, parent, false)

        return HomeFragViewHolder(view, mBooksItemDelegate)
    }

    override fun onBindViewHolder(holder: HomeFragViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }


}