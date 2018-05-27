package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.model.Books

class BookDetailActivity : AppCompatActivity() {

    companion object {
        val BOOK_KEY = "book_key"

        @JvmStatic fun getIntent(context: Context, book: Books): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(BOOK_KEY,book)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val currency = intent.getParcelableExtra<Books>(BOOK_KEY)

        author.setText(currency.authorName)
        booktitle.setText(currency.bookName)
    }
}
