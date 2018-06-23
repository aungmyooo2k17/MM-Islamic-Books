package org.m2cs.mmislamicbooks.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import org.m2cs.mmislamicbooks.database.listener.OnDatabaseChangedListener
import android.content.ContentValues
import android.util.Log
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import org.m2cs.mmislamicbooks.database.DbHelper.DBHelperItem


class DbHelper(private val mContext: Context) : SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    abstract class DBHelperItem : BaseColumns {
        companion object {
            val TABLE_NAME = "download_books"

            val COLUMN_NAME_BOOK_NAME = "book_name"
            val COLUMN_NAME_BOOK_FILE_PATH = "file_path"
            val COLUMN_NAME_BOOK_ID = "book_id"
            val COLUMN_NAME_BOOK_COVER = "book_cover"
            val COLUMN_NAME_BOOK_DETAIL_COVER = "book_detail_cover"
            val COLUMN_NAME_BOOK_SUBTITLE = "book_sub_title"
            val COLUMN_NAME_BOOK_DESC = "book_desc"
            val COLUMN_NAME_BOOK_AUTHOR = "book_author"

        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {

        val LOG_TAG = "DBHelper"

        var mOnDatabaseChangedListener: OnDatabaseChangedListener? = null

        val DATABASE_NAME = "saved_books.db"
        val DATABASE_VERSION = 1

        val TEXT_TYPE = " TEXT"
        val COMMA_SEP = ","
        val SQL_CREATE_ENTRIES = "CREATE TABLE " + DBHelperItem.TABLE_NAME + " (" +
                DBHelperItem.COLUMN_NAME_BOOK_ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_NAME + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_COVER + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_DETAIL_COVER + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_SUBTITLE + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_DESC + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_AUTHOR + TEXT_TYPE + COMMA_SEP +
                DBHelperItem.COLUMN_NAME_BOOK_FILE_PATH + TEXT_TYPE + ") "


        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBHelperItem.TABLE_NAME

        fun setOnDatabaseChangedListener(listener: OnDatabaseChangedListener) {
            mOnDatabaseChangedListener = listener
        }
    }

    fun addBook(mBookVo: BookVO, mFilePath: String): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        Log.i("hi", "${mBookVo.bookName},$mFilePath")
        var bookId = mBookVo.bookId.toInt()

        cv.put(DBHelperItem.COLUMN_NAME_BOOK_ID, bookId)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_NAME, mBookVo.bookName)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_COVER, mBookVo.bookCover)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_DETAIL_COVER, mBookVo.bookDetailCover)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_SUBTITLE, mBookVo.bookSubTitle)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_DESC, mBookVo.bookDesc)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_AUTHOR, mBookVo.authorId)
        cv.put(DBHelperItem.COLUMN_NAME_BOOK_FILE_PATH, mFilePath)
        val rowId = db.insert(DBHelperItem.TABLE_NAME, null, cv)
        db.close()
        if (mOnDatabaseChangedListener != null) {
            mOnDatabaseChangedListener!!.onNewDatabaseEntryAdded();
        }
        Log.i("DbHelperInsert", "${rowId}")

        return rowId;

    }

    fun getCount(): Int {
        val db = readableDatabase
        val projection = arrayOf(DBHelperItem.COLUMN_NAME_BOOK_ID)
        val c = db.query(DBHelperItem.TABLE_NAME, projection, null, null, null, null, null)
        val count = c.count
        c.close()
        return count
    }

    fun removeItemWithId(id: Int) {
        val db = writableDatabase
        val whereArgs = arrayOf(id.toString())
        db.delete(DBHelperItem.TABLE_NAME, "book_id=?", whereArgs)
    }

    fun getFilePath(id: Int): String {
        val db = readableDatabase
        var label: String = ""
        val cursor = db.rawQuery("SELECT ${DBHelperItem.COLUMN_NAME_BOOK_FILE_PATH} FROM ${DBHelperItem.TABLE_NAME} WHERE ${DBHelperItem.COLUMN_NAME_BOOK_ID}= $id ", null)
//        return cursor.getString(cursor.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_FILE_PATH))

        if (cursor != null) {
            cursor.moveToFirst()
            label = cursor.getString(0)
        }

        return label
    }

    fun getBookTitle(): List<String> {
        val db = readableDatabase
        var bookList = arrayListOf<String>()
        val cursor = db.rawQuery("SELECT ${DBHelperItem.COLUMN_NAME_BOOK_NAME} FROM ${DBHelperItem.TABLE_NAME}", null)
        cursor.moveToFirst()
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {

                bookList.add(cursor.getString(0))
                Log.i("TAG", cursor.getString(0))


                cursor.moveToNext()
            }
        }

        //        while (!cursor.isAfterLast()) {
//            bookList.add(cursor.getString(cursor.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_NAME)))
//            cursor.moveToNext()
//        }
        cursor.close()
        return bookList

    }

    fun getItemAt(position: Int): DownloadVO? {
        var db = readableDatabase
        val c = db.query(DBHelperItem.TABLE_NAME, null, null, null, null, null, null)
        if (c.moveToPosition(position)) {
            var dowloadVo = DownloadVO()
            dowloadVo.downloadBookName = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_NAME))
            dowloadVo.downloadBookCover = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_COVER))
            dowloadVo.downlaodBookDesc = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_DESC))
            dowloadVo.downloadBookDetailCover = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_DETAIL_COVER))
            dowloadVo.downloadBookId = c.getInt(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_ID))
            dowloadVo.mFilePath = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_FILE_PATH))
            dowloadVo.downloadBookAuthor = c.getString(c.getColumnIndex(DBHelperItem.COLUMN_NAME_BOOK_AUTHOR))
            c.close()
            return dowloadVo
        }

        return null


    }

    fun getContext(): Context {
        return mContext
    }
}
