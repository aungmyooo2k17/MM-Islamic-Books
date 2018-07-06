package org.m2cs.mmislamicbooks.utils

import android.net.Uri
import android.app.DownloadManager
import android.database.Cursor
import java.lang.reflect.Method
import android.app.DownloadManager.COLUMN_LOCAL_FILENAME
import android.app.DownloadManager.COLUMN_LOCAL_URI
import android.os.Build




class DownloadManagerPro(downloadManager: DownloadManager) {

    companion object {
        val CONTENT_URI = Uri.parse("content://downloads/my_downloads")

        val COLUMN_LOCAL_FILENAME = "local_filename"

        val COLUMN_LOCAL_URI = "local_uri"

        val METHOD_NAME_PAUSE_DOWNLOAD = "pauseDownload"
        val METHOD_NAME_RESUME_DOWNLOAD = "resumeDownload"

         var isInitPauseDownload = false
         var isInitResumeDownload = false

         var pauseDownload: Method? = null
         var resumeDownload: Method? = null

        fun initPauseMethod() {
            if (isInitPauseDownload)
            {
                return
            }
            isInitPauseDownload=true
            try {
                pauseDownload=DownloadManager::class.java.getMethod(METHOD_NAME_PAUSE_DOWNLOAD,LongArray::class.java)
            }catch (e:Exception)
            {
                e.printStackTrace()
            }
        }
        fun initResumeMethod(){
            if (isInitResumeDownload) {
                return
            }
            isInitResumeDownload = true
            try {
                resumeDownload = DownloadManager::class.java.getMethod(METHOD_NAME_RESUME_DOWNLOAD, LongArray::class.java)
            } catch (e: Exception) {
                // accept all exception
                e.printStackTrace()
            }

        }
    }

    var downloadManager: DownloadManager? = null

    init {
        this.downloadManager = downloadManager
    }

    fun getStatusById(downloadId: Long): Int {
        return getInt(downloadId, DownloadManager.COLUMN_STATUS)
    }

    fun getDownloadBytes(downloadId: Long): IntArray {
        val bytesAndStatus = getBytesAndStatus(downloadId)
        return intArrayOf(bytesAndStatus[0], bytesAndStatus[1])
    }

    fun getBytesAndStatus(downloadId: Long): IntArray {
        val bytesAndStatus = intArrayOf(-1, -1, 0)
        val query = DownloadManager.Query().setFilterById(downloadId)
        var c: Cursor? = null
        try {
            c = downloadManager!!.query(query)
            if (c != null && c.moveToFirst()) {
                bytesAndStatus[0] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                bytesAndStatus[1] = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                bytesAndStatus[2] = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS))
            }
        } finally {
            if (c != null) {
                c.close()
            }
        }
        return bytesAndStatus
    }

    fun pauseDownload(vararg ids: Long): Int {
        initPauseMethod()
        if (pauseDownload == null) {
            return -1
        }
        try {
            return (pauseDownload!!.invoke(downloadManager, ids) as Int).toInt()
        } catch (e: Exception) {

            e.printStackTrace()
        }
        return -1
    }

    fun resumeDownload(vararg ids: Long): Int {
        if (resumeDownload == null) {
            return -1
        }
        try {
            return (resumeDownload!!.invoke(downloadManager, ids) as Int).toInt()

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return -1
    }

    fun getFileName(downloadId: Long): String {
        return getString(downloadId, if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            COLUMN_LOCAL_URI
        else
            COLUMN_LOCAL_FILENAME)
    }


    fun getUri(downloadId: Long): String {
        return getString(downloadId, DownloadManager.COLUMN_URI)
    }

    fun isExistPauseAndResumeMethod(): Boolean {
        initPauseMethod()
        initResumeMethod()
        return pauseDownload != null && resumeDownload != null
    }

    private fun initPauseMethod() {
        if (isInitPauseDownload) {
            return
        }

        isInitPauseDownload = true
        try {
            pauseDownload = DownloadManager::class.java.getMethod(METHOD_NAME_PAUSE_DOWNLOAD, LongArray::class.java)
        } catch (e: Exception) {
            // accept all exception
            e.printStackTrace()
        }

    }

    private fun initResumeMethod() {
        if (isInitResumeDownload) {
            return
        }

        isInitResumeDownload = true
        try {
            resumeDownload = DownloadManager::class.java.getMethod(METHOD_NAME_RESUME_DOWNLOAD, LongArray::class.java)
        } catch (e: Exception) {
            // accept all exception
            e.printStackTrace()
        }

    }

    fun getPausedReason(downloadId: Long): Int {
        return getInt(downloadId, DownloadManager.COLUMN_REASON)
    }


    fun getErrorCode(downloadId: Long): Int {
        return getInt(downloadId, DownloadManager.COLUMN_REASON)
    }


    fun getReason(downloadId: Long): Int {
        return getInt(downloadId, DownloadManager.COLUMN_REASON)
    }


    private fun getInt(downloadId: Long, columnName: String): Int {
        val query = DownloadManager.Query().setFilterById(downloadId)
        var result = -1
        var c: Cursor? = null
        try {
            c = downloadManager!!.query(query)
            if (c != null && c.moveToFirst()) {
                result = c.getInt(c.getColumnIndex(columnName))
            }
        } finally {
            if (c != null) {
                c.close()
            }
        }
        return result
    }

     private fun getString(downloadId: Long, columnName: String): String {
        val query = DownloadManager.Query().setFilterById(downloadId)
        var result: String = ""
        var c: Cursor? = null
        try {
            c = downloadManager!!.query(query)
            if (c != null && c.moveToFirst()) {
                result = c.getString(c.getColumnIndex(columnName))
            }
        } finally {
            if (c != null) {
                c.close()
            }
        }
        return result
    }


    class RequestPro(uri: Uri) : DownloadManager.Request(uri) {

        fun setNotiClass(className: String) {
            synchronized(this) {

                if (!isInitNotiClass) {
                    isInitNotiClass = true
                    try {
                        setNotiClass = DownloadManager.Request::class.java.getMethod(METHOD_NAME_SET_NOTI_CLASS, CharSequence::class.java)
                    } catch (e: Exception) {
                        // accept all exception
                        e.printStackTrace()
                    }

                }
            }

            if (setNotiClass != null) {
                try {
                    setNotiClass!!.invoke(this, className)
                } catch (e: Exception) {

                    e.printStackTrace()
                }

            }
        }


        fun setNotiExtras(extras: String) {
            synchronized(this) {

                if (!isInitNotiExtras) {
                    isInitNotiExtras = true
                    try {
                        setNotiExtras = DownloadManager.Request::class.java.getMethod(METHOD_NAME_SET_NOTI_EXTRAS, CharSequence::class.java)
                    } catch (e: Exception) {
                        // accept all exception
                        e.printStackTrace()
                    }

                }
            }

            if (setNotiExtras != null) {
                try {
                    setNotiExtras!!.invoke(this, extras)
                } catch (e: Exception) {

                    e.printStackTrace()
                }

            }
        }

        companion object {

            val METHOD_NAME_SET_NOTI_CLASS = "setNotiClass"
            val METHOD_NAME_SET_NOTI_EXTRAS = "setNotiExtras"

            private var isInitNotiClass = false
            private var isInitNotiExtras = false

            private var setNotiClass: Method? = null
            private var setNotiExtras: Method? = null
        }
    }
}