package org.m2cs.mmislamicbooks.receiver

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import org.m2cs.mmislamicbooks.App
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.activity.BookDetailActivity.Companion.KEY_NAME_DOWNLOAD_ID
import org.m2cs.mmislamicbooks.service.DownloadService
import org.m2cs.mmislamicbooks.utils.PreferencesUtils
import org.m2cs.mmislamicbooks.activity.MainActivity


class DownloadCompleteReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        var downloadIds = PreferencesUtils.getArrayList(context!!, "downIds")


        val completeDownloadId = intent!!.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)


        for (id in downloadIds) {
            if (completeDownloadId == id) {
                if (context != null) {

                    App.downIds.remove(id)
                    val intentService = Intent(context, DownloadService::class.java)
                    intentService.putExtra("downloadObject", completeDownloadId)
                    context.startService(intentService)
                }

                break

            }
        }


    }
}