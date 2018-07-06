package org.m2cs.mmislamicbooks.database.roomdatabase

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.Database
import android.content.Context
import org.m2cs.mmislamicbooks.data.dao.BookDao
import org.m2cs.mmislamicbooks.data.model.Book


@Database(entities = arrayOf(Book::class), version = 1)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        private var INSTANCE: BookRoomDatabase? = null

        internal fun getDatabase(context: Context): BookRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(BookRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                BookRoomDatabase::class.java, "book_database")
                                // Wipes and rebuilds instead of migrating
                                // if no Migration object.
                                // Migration is not part of this practical.
                                .fallbackToDestructiveMigration()
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}