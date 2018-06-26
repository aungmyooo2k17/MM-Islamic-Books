package org.m2cs.mmislamicbooks.adapter

import android.content.Context
import android.view.ViewGroup
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.viewholder.DownloadViewHolder
import org.m2cs.mmislamicbooks.viewholder.HomeFragViewHolder
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.widget.Adapter
import org.m2cs.mmislamicbooks.database.DbHelper
import org.m2cs.mmislamicbooks.delegates.DownloadItemDelegate


class DownloadAdapter(val context: Context?, val mDownloadItemDelegate: DownloadItemDelegate) : RecyclerView.Adapter<DownloadViewHolder>() {


    private val LOG_TAG = "FileViewerAdapter"

    lateinit var mDatabase: DbHelper

     lateinit var mDownloadVo: DownloadVO

    init {
        mDatabase = DbHelper(context!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownloadViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.download_content, parent, false)

        return DownloadViewHolder(view, mDownloadItemDelegate)
    }

    override fun onBindViewHolder(holder: DownloadViewHolder, position: Int) {
        mDownloadVo = getItem(position)!!

        holder.bind(mDownloadVo)
    }

    fun getItem(position: Int): DownloadVO? {
        Log.i("Hi", "${mDatabase.getCount()}")

        return mDatabase.getItemAt(position)
    }



    override fun getItemCount(): Int {
        return mDatabase.getCount()
    }



}