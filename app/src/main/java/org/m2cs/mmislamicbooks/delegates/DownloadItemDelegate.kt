package org.m2cs.mmislamicbooks.delegates

import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import java.text.FieldPosition

interface DownloadItemDelegate {

    fun onTapDelete(downloadBook:DownloadVO,position:Int)
    fun onTapRead(downloadBook: DownloadVO)
}