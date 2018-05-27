package org.m2cs.mmislamicbooks.viewholder

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.home_content_view.view.*
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.model.Books

class HomeFragViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




    private val tvTitle = itemView.bookTitle
    private val tvAuthor = itemView.bookAuthor
    private val imgThumbnail = itemView.thumbnail
    val context: Context = itemView.context

    private lateinit var mBook: Books

    fun bind(book: Books) {
        mBook = book

        tvAuthor.text=book.authorName
        tvTitle.text=book.bookName
        Glide.with(itemView.context).load(book.imageLink).into(imgThumbnail)
        imgThumbnail.setOnClickListener{

            context.startActivity(BookDetailActivity.getIntent(context, book))        }

    }


}