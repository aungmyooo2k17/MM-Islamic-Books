package org.m2cs.mmislamicbooks.repository

import android.app.Application


class BookRepository internal constructor(application: Application) {
//
//    private val mBookDao: BookDao
//    internal val allBooks: LiveData<List<Book>>
//
//    init {
//        val db = BookRoomDatabase.getDatabase(application)
//        mBookDao = db!!.bookDao()
//        allBooks = mBookDao.getAllBooks()
//    }
//
//    fun insert(book: Book) {
//        insertAsyncTask(mBookDao).execute(book)
//    }
//
//    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: BookDao) : AsyncTask<Book, Void, Void>() {
//
//        override fun doInBackground(vararg params: Book): Void? {
//            mAsyncTaskDao.insert(params[0])
//            return null
//        }
//    }
}