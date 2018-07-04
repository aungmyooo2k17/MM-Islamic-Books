package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.BookSearchViewHolder
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class BookSearchAdapter (val context: Context?, val mBooksItemDelegate: BooksItemDelegate) : BaseRecyclerAdapter<BookSearchViewHolder, BookVO>(context!!) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        val view = mLayoutInflater.inflate(R.layout.view_item_book_search, parent, false)



        return BookSearchViewHolder(view, mBooksItemDelegate)
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }

}