package org.m2cs.mmislamicbooks.service

import android.app.IntentService
import android.content.Intent
import android.os.Environment
import android.util.Log
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.database.DbHelper
import org.m2cs.mmislamicbooks.utils.PreferencesUtils

class DownloadService() : IntentService("MyService") {


    override fun onHandleIntent(intent: Intent?) {
        var db: DbHelper = DbHelper(this)
        var bookVOString = ""
        var downloadFileName = ""

        var downloadFilePath = Environment.getExternalStorageDirectory().absolutePath

        if (intent != null) {
            bookVOString = intent.extras.getLong("downloadObject").toString()
        }

        var downloadBookVo = PreferencesUtils.getBookVo(this, bookVOString)
        downloadFileName = downloadBookVo.bookName + ".pdf"
        downloadFilePath += "/${getString(R.string.app_name)}/$downloadFileName"
        db.addBook(downloadBookVo, downloadFilePath)
        PreferencesUtils.removeValue(this, downloadBookVo.bookId)
        PreferencesUtils.removeValue(this, bookVOString)

    }
}