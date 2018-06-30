package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.view.View
import kotlinx.android.synthetic.main.view_item_book_search.view.*
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate

class BookSearchViewHolder(itemView: View, val mBooksItemDelegate: BooksItemDelegate) : BaseViewHolder<BookVO>(itemView){


    private val tvBookTitle = itemView.tv_book_item_title
    val context: Context = itemView.context



    override fun bind(data: BookVO) {
        tvBookTitle.text = data.bookName
    }

}