package org.m2cs.mmislamicbooks.models

import android.util.Log

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.network.response.BookListResponse

import java.util.HashMap

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.m2cs.mmislamicbooks.events.DataEvents
import android.app.Application

class BookModel private constructor() : BaseModel() {
    private var mBooks: Map<String, BookVO>
    private var bookList: List<BookVO> = ArrayList<BookVO>()

    init {
        mBooks = HashMap()

    }

    fun loadBook() {
        val personListResponseObservable = theApi.loadBook()

        Log.i("Retry","Reloading")

        personListResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookListResponse> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("TAG", "onSubscribe: " + d.isDisposed)
                    }

                    override fun onNext(bookListResponse: BookListResponse) {
                        Log.d("Tag", "onNext " + bookListResponse)

                        bookList = bookListResponse.getBooks!!
                        Log.i("ReloadingBookModel", "${bookList.get(0).bookName}")


                        mBooks = HashMap()

                        for (book: BookVO in bookListResponse.getBooks!!) {
                            (mBooks as HashMap<String, BookVO>).put(book.bookId, book)


                        }


                        var event: DataEvents.BookLoadedEvent = DataEvents.BookLoadedEvent(bookListResponse.getBooks!!)
                        EventBus.getDefault().post(event)


                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG", "onError: " + e.message)
                    }

                    override fun onComplete() {
                        Log.d("TAG", "onComplete: ")
                    }
                })
    }

    companion object {
        private var sObjInstance: BookModel? = null

        fun getsObjectInstance(): BookModel {
            if (sObjInstance == null) {
                sObjInstance = BookModel()
            }
            return sObjInstance as BookModel
        }
    }


    fun getBookById(questionId: String): BookVO? {
        return mBooks.get(questionId)
    }

    fun getBookByCategoryId(categoryId: String): List<BookVO> {
        var mBook: ArrayList<BookVO> = ArrayList<BookVO>()
        for (book: BookVO in bookList) {
            if (book.categoryId.equals(categoryId)) {
                mBook.add(book)
            }
        }

        return mBook
    }

    fun getBookByCategoryId(categoryId: String, mBookList: List<BookVO>): List<BookVO> {
        var mBook: ArrayList<BookVO> = ArrayList<BookVO>()
        for (book: BookVO in mBookList) {
            if (book.categoryId.equals(categoryId)) {
                mBook.add(book)
            }
        }

        return mBook
    }
}
