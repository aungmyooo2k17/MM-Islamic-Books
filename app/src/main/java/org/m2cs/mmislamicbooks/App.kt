package org.m2cs.mmislamicbooks

import android.app.Application

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.models.BookModel

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        BookModel.getsObjectInstance().loadBook()

    }

    companion object {

        var TAG = "MMISLAMICBOOK"
        var globalBookList: List<BookVO>? = ArrayList<BookVO>()
    }
}
