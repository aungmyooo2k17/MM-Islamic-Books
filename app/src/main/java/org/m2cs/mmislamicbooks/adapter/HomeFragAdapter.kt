package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.nfc.Tag
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.model.Books
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder

class HomeFragAdapter(val context: Context?) : RecyclerView.Adapter<HomeFragViewHolder>() {

    val mBookList: ArrayList<Books> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_content_view, parent, false)
        return HomeFragViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mBookList.size
    }

    override fun onBindViewHolder(holder: HomeFragViewHolder, position: Int) {
        holder.bind(mBookList[position])
    }

    fun replaceData(testList: List<Books>) {
        mBookList.clear()
        mBookList.addAll(testList)
        notifyDataSetChanged()
    }

}