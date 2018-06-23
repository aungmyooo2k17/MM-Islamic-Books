package org.m2cs.mmislamicbooks.utils

import android.app.DownloadManager
import java.text.DecimalFormat


object DownloadUtils {
    val DOUBLE_DECIMAL_FORMAT = DecimalFormat("0.##")

    val MB_2_BYTE = 1024 * 1024
    val KB_2_BYTE = 1024


    fun getBookSize(size: Long): CharSequence {
        if (size <= 0) {
            return "0M"
        }

        return if (size >= MB_2_BYTE) {
            StringBuilder(16).append(DOUBLE_DECIMAL_FORMAT.format(size.toDouble() / MB_2_BYTE)).append("M")
        } else if (size >= KB_2_BYTE) {
            StringBuilder(16).append(DOUBLE_DECIMAL_FORMAT.format(size.toDouble() / KB_2_BYTE)).append("K")
        } else {
            size.toString() + "B"
        }
    }

    fun getNotiPercent(progress: Long, max: Long): String {
        var rate = 0
        if (progress <= 0 || max <= 0) {
            rate = 0
        } else if (progress > max) {
            rate = 100
        } else {
            rate = (progress.toDouble() / max * 100).toInt()
        }
        return StringBuilder(16).append(rate).append("%").toString()
    }

    fun isDownloading(downloadManagerStatus: Int): Boolean {
        return (downloadManagerStatus == DownloadManager.STATUS_RUNNING
                || downloadManagerStatus == DownloadManager.STATUS_PAUSED
                || downloadManagerStatus == DownloadManager.STATUS_PENDING)
    }
}