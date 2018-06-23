package org.m2cs.mmislamicbooks.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.activity.BookDetailActivity.Companion.bookVO
import org.m2cs.mmislamicbooks.database.DbHelper

class DownloadService() : IntentService("MyService") {


    override fun onHandleIntent(intent: Intent?) {
        var db:DbHelper=DbHelper(this)
        Log.i("Down","start")

        db.addBook(bookVO,BookDetailActivity.mFilePath)
    }
}