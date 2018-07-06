package org.m2cs.mmislamicbooks.viewmodels

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.AndroidViewModel
import org.m2cs.mmislamicbooks.data.model.Book
import org.m2cs.mmislamicbooks.repository.BookRepository


class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: BookRepository

    internal val allWords: LiveData<List<Book>>

    init {
        mRepository = BookRepository(application)
        allWords = mRepository.allBooks
    }

    fun insert(book: Book) {
        mRepository.insert(book)
    }
}