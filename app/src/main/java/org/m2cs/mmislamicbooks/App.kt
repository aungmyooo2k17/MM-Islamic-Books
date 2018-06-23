package org.m2cs.mmislamicbooks

import android.app.Application
import android.app.DownloadManager
import android.content.IntentFilter

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.receiver.DownloadCompleteReceiver

class App : Application() {

    lateinit var completeReceiver:DownloadCompleteReceiver

    override fun onCreate() {
        super.onCreate()
        completeReceiver= DownloadCompleteReceiver()
        registerReceiver(completeReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
//        BookModel.getsObjectInstance().loadBook()

    }



    companion object {

        var TAG = "MMISLAMICBOOK"
        var globalBookList: List<BookVO>? = ArrayList<BookVO>()
    }
}
