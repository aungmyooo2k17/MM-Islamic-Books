//package org.m2cs.mmislamicbooks.data.dao
//
//import android.arch.lifecycle.LiveData
//import android.arch.persistence.room.Dao
//import android.arch.persistence.room.Insert
//import android.arch.persistence.room.Query
//import org.m2cs.mmislamicbooks.data.model.Book
//
//@Dao
//interface BookDao{
//    @Insert
//    fun insert(book: Book)
//
//    @Query("DELETE FROM book_table")
//    fun deleteAll()
//
//    @Query("SELECT * from book_table")
//    fun getAllBooks(): LiveData<List<Book>>
//
//}