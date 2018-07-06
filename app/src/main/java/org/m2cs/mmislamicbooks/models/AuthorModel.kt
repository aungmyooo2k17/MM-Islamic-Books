package org.m2cs.mmislamicbooks.models

import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.m2cs.mmislamicbooks.data.vo.AuthorVO
import org.m2cs.mmislamicbooks.network.response.AuthorListResponse

class AuthorModel private constructor() : BaseModel() {

    private val mAuthors: Map<String, AuthorVO>

    init {
        mAuthors = HashMap()
    }

    fun loadAuthor() {
        val authorListResponseObservable = theApi.loadAuthor()
        authorListResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<AuthorListResponse> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: AuthorListResponse) {
                        Log.d("Tag", "onNext "+t)
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }


                })
    }

    companion object {
        private var sObjInstance: AuthorModel? = null

        fun getsObjectInstance(): AuthorModel {
            if (sObjInstance == null) {
                sObjInstance = AuthorModel()
            }
            return sObjInstance as AuthorModel
        }
    }

}