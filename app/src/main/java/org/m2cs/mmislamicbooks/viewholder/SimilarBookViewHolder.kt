package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.similar_book_content.view.*
import kotlinx.android.synthetic.main.view_item_home_content.view.*
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate

class SimilarBookViewHolder(itemView: View, val mBooksItemDelegate: BooksItemDelegate) : BaseViewHolder<BookVO>(itemView) {


    private val imgBookCover = itemView.iv_similar_book_cover
//    private val tvTitle = itemView.tv_similar_book_title_detail
//    private val tvAuthor = itemView.tv_similar_book_author_detail
    val context: Context = itemView.context


    override fun bind(data: BookVO) {
        Glide.with(itemView.context).load(data.bookCover).into(imgBookCover)
//        tvTitle.setText(data.bookName)
//        tvAuthor.setText(data.authorId)
        itemView.setOnClickListener(View.OnClickListener {
            mBooksItemDelegate.onTapBookItem(data)
        })
    }
}