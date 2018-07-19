package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder
import org.m2cs.mmislamicbooks.viewholder.SimilarBookViewHolder

class SimilarBookAdapter(val context: Context?, val mBooksItemDelegate: BooksItemDelegate) : BaseRecyclerAdapter<SimilarBookViewHolder, BookVO>(context!!) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarBookViewHolder {
        val view = mLayoutInflater.inflate(R.layout.similar_book_content, parent, false)

        return SimilarBookViewHolder(view, mBooksItemDelegate)
    }

    override fun onBindViewHolder(holder: SimilarBookViewHolder, position: Int) {
        holder.bind(mData!!.get(position))

    }
}