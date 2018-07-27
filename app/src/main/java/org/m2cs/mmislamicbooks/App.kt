package org.m2cs.mmislamicbooks

import android.app.Application
import android.app.DownloadManager
import android.content.IntentFilter
import android.os.Environment
import android.util.Log

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.database.DbHelper
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.receiver.DownloadCompleteReceiver
import java.io.File
import android.graphics.Typeface
import android.R.attr.typeface
import android.content.res.AssetManager
import org.m2cs.mmislamicbooks.data.vo.CategoryVO
import org.m2cs.mmislamicbooks.models.CategoryModel
import java.util.*
import kotlin.collections.ArrayList


class App : Application() {

    lateinit var completeReceiver: DownloadCompleteReceiver
    lateinit var mDb: DbHelper

    companion object {

        var TAG = "MMISLAMICBOOK"
        var globalBookList: List<BookVO>? = ArrayList<BookVO>()
        var globalCateogryList: List<CategoryVO>? = ArrayList<CategoryVO>()
        var downIds = arrayListOf<Long>()
         var typeface:Typeface? = null
    }
    override fun onCreate() {
        super.onCreate()
        CategoryModel.getObjectInstance().loadCategory()
        completeReceiver = DownloadCompleteReceiver()
        registerReceiver(completeReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        mDb = DbHelper(applicationContext)
        Log.i("App", "${mDb.getCount()}")
        if (mDb.getCount() == 0) {
            var root = Environment.getExternalStorageDirectory().toString()
            var file = File(root, "/MM Islamic Books")
            file.delete()
        }

        val am = this.applicationContext.assets

         typeface = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "ZawgyiOne.ttf"))


    }


}
