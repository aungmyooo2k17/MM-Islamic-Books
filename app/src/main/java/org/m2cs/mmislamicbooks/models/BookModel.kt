package org.m2cs.mmislamicbooks.models

import android.util.Log

import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.network.response.BookListResponse

import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BookModel private constructor() : BaseModel() {
    private val mQuestions: Map<String, BookVO>

    init {
        mQuestions = HashMap()

    }

    fun loadPerson() {
        val personListResponseObservable = theApi.loadQuestion()

        personListResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<BookListResponse> {
                    override fun onSubscribe(d: Disposable) {
                        Log.d("TAG", "onSubscribe: " + d.isDisposed)
                    }

                    override fun onNext(personListResponse: BookListResponse) {
                        Log.d("Tag", "onNext "+personListResponse)
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
}
