package org.m2cs.mmislamicbooks.models

import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.m2cs.mmislamicbooks.data.vo.CategoryVO
import org.m2cs.mmislamicbooks.network.response.CategoryListResponse
import kotlin.math.log

class CategoryModel private constructor(): BaseModel(){

    private val mCategorys: Map<String, CategoryVO>

    init {
        mCategorys = HashMap()
    }

    fun loadCategory(){
        var categoryListResponseObservable = theApi.loadCategory()

        categoryListResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<CategoryListResponse>{
                    override fun onSubscribe(d: Disposable) {
                        Log.d("TAG", "onSubscribe: " + d.isDisposed)
                    }

                    override fun onNext(categoryListResponse: CategoryListResponse) {
                        Log.d("Tag", "onNext "+categoryListResponse)
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
        private var sObjInstance: CategoryModel? = null

        fun getObjectInstance() : CategoryModel{

            if (CategoryModel.sObjInstance == null) {
                CategoryModel.sObjInstance = CategoryModel()
            }
            return CategoryModel.sObjInstance as CategoryModel

        }
    }


}