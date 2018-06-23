package org.m2cs.mmislamicbooks.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.activity.BookDetailActivity.Companion.KEY_NAME_DOWNLOAD_ID
import org.m2cs.mmislamicbooks.activity.BookDetailActivity.Companion.downloadId
import org.m2cs.mmislamicbooks.service.DownloadService
import org.m2cs.mmislamicbooks.utils.PreferencesUtils
import org.m2cs.mmislamicbooks.activity.MainActivity



class DownloadCompleteReceiver: BroadcastReceiver() {

    lateinit  var bookDetailActivity:BookDetailActivity
    lateinit var downloadService:DownloadService


    override fun onReceive(context: Context?, intent: Intent?) {

        bookDetailActivity = BookDetailActivity()
        downloadService= DownloadService()

        val completeDownloadId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        Log.i("DownloadBroadcast","$completeDownloadId")
        Log.i("DownloadBroadcast","${downloadId}")
        Log.i("DownloadBroadcast", KEY_NAME_DOWNLOAD_ID)


        if (completeDownloadId == downloadId)
        {

            if (context != null) {

                PreferencesUtils.removeValue(context,KEY_NAME_DOWNLOAD_ID)
                val intentService = Intent(context, DownloadService::class.java)
//                intentService.setAction(ReminderTask.increment_water_count)
                context.startService(intentService)
            }



        }
    }
}