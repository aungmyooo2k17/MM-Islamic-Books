package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.R.id.*
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.model.Books

class BookDetailActivity : AppCompatActivity() {

    val books: ArrayList<Books> = ArrayList()
    private lateinit var mHomeAdapter: HomeFragAdapter

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

        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        tool_bar.title="Book Detail"
//        tool_bar.setTitleTextColor(resources.getColor(R.color.primaryText))
//


        initializedArray()

        val book = intent.getParcelableExtra<Books>(BOOK_KEY)

        detailAuthor.setText(book.authorName)
        bookTitle.setText(book.bookName)

        rv_similar_book.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mHomeAdapter = HomeFragAdapter(this)
        mHomeAdapter.replaceData(books)
        rv_similar_book.adapter = mHomeAdapter
        rv_similar_book.isNestedScrollingEnabled = false
        rv_similar_book.setFocusable(false);

    }

    private fun initializedArray()
    {
        for (i in 1..15)
        {
            books.add(Books())
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home)
        {
            finish()
        }
        return true;
    }

    public fun AdjustFavorite(view: View)
    {


    }

    public fun DownloadTask(view: View)
    {


    }
}
