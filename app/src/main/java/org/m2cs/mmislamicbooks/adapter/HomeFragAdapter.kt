package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class HomeFragAdapter(val context: Context?, val mBooksItemDelegate: BooksItemDelegate) : BaseRecyclerAdapter<HomeFragViewHolder, BookVO>(context!!) {

//    val mBookList: ArrayList<Book> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragViewHolder {
        val view = mLayoutInflater.inflate(R.layout.home_content_view, parent, false)

        return HomeFragViewHolder(view, mBooksItemDelegate)
    }

//    override fun getItemCount(): Int {
//        return mBookList.size
//    }

    override fun onBindViewHolder(holder: HomeFragViewHolder, position: Int) {
        holder.bind(mData!!.get(position))
    }

//    fun replaceData(testList: List<Book>) {
//        mBookList.clear()
//        mBookList.addAll(testList)
//        notifyDataSetChanged()
//    }

}