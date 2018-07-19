package org.m2cs.mmislamicbooks.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_item_download_content.view.*
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.delegates.DownloadItemDelegate

class DownloadViewHolder(itemView: View, val mdownloadItemDelegate: DownloadItemDelegate) : RecyclerView.ViewHolder(itemView) {
    val tvDownloadTitle = itemView.downloadBookTitle
    val tvDownloadAuthor = itemView.downloadBookAuthor
    val tvDownloadCover = itemView.downloadBookCover
    val btnDelete = itemView.btn_del
    val btnRead = itemView.btn_read


    fun bind(data: DownloadVO) {
        tvDownloadTitle.setText(data.downloadBookName)
        tvDownloadAuthor.setText(data.downloadBookAuthor)

        Glide.with(itemView.context).load(data.downloadBookCover).into(tvDownloadCover)
        btnDelete.setOnClickListener(View.OnClickListener { mdownloadItemDelegate.onTapDelete(data, adapterPosition) })
        btnRead.setOnClickListener(View.OnClickListener { mdownloadItemDelegate.onTapRead(data) })

    }
}