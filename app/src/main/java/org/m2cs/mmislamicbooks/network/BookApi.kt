package org.m2cs.mmislamicbooks.network

import io.reactivex.Observable
import org.m2cs.mmislamicbooks.network.response.AuthorListResponse
import org.m2cs.mmislamicbooks.network.response.BookListResponse
import org.m2cs.mmislamicbooks.network.response.CategoryListResponse
import org.m2cs.mmislamicbooks.utils.AppConstants
import retrofit2.http.GET


interface BookApi {
    @GET(AppConstants.BOOK_LIST)
    fun loadQuestion(): Observable<BookListResponse>

    @GET(AppConstants.AUTHOR_LIST)
    fun loadAuthor(): Observable<AuthorListResponse>

    @GET(AppConstants.CATEGORY_LIST)
    fun loadCategory(): Observable<CategoryListResponse>
}