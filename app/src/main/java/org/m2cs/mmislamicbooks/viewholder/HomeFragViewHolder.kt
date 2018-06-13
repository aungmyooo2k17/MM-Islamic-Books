package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_content_view.view.*
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate

class HomeFragViewHolder(itemView: View,val mBooksItemDelegate: BooksItemDelegate) : BaseViewHolder<BookVO>(itemView) {


    private val tvTitle = itemView.tv_book_title_detail
    private val tvAuthor = itemView.tv_book_author_detail
    private val imgBookCover = itemView.iv_book_cover
    val context: Context = itemView.context


//    private lateinit var mBook: Books


    /*
    fun bind(book: Books) {
        mBook = book

        tvAuthor.text=book.authorName
        tvTitle.text=book.bookName
        Glide.with(itemView.context).load(book.imageLink).into(imgThumbnail)
        imgThumbnail.setOnClickListener{

            context.startActivity(BookDetailActivity.newIntent(context, book))        }

    }
    */



    override fun bind(data: BookVO) {
        tvTitle.text = data.bookName
        tvAuthor.text = data.authorId
        Glide.with(itemView.context).load(data.bookCover).into(imgBookCover)
        itemView.setOnClickListener(View.OnClickListener {
            mBooksItemDelegate.onTapBookItem(data)
        })

    }


}