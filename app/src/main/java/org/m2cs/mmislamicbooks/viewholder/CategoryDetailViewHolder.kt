package org.m2cs.mmislamicbooks.viewholder

import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_book_list_item.view.*
import org.m2cs.mmislamicbooks.App
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate

class CategoryDetailViewHolder(itemView: View, val mBooksItemDelegate: BooksItemDelegate) : BaseViewHolder<BookVO>(itemView) {

    private val tvTitle = itemView.categoryDetailBookTitle
    private val tvAuthor = itemView.categoryDetailBookAuthor
    private val ivBookCover = itemView.categoryDetailBookCover


    override fun bind(data: BookVO) {
        tvTitle.setTypeface(App.typeface)
        tvAuthor.setTypeface(App.typeface)
        tvTitle.text = data.bookName
        tvAuthor.text = data.authorId
        Glide.with(itemView.context).load(data.bookCover).into(ivBookCover)
        itemView.setOnClickListener(View.OnClickListener {
            mBooksItemDelegate.onTapBookItem(data)
        })
    }

}