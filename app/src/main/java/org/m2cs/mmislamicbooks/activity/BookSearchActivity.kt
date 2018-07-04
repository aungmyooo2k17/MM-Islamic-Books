package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_book_search.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.BookSearchAdapter
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.events.DataEvents
import org.m2cs.mmislamicbooks.models.BookModel

class BookSearchActivity : AppCompatActivity(), BooksItemDelegate {


    public lateinit var mBookSearchAdapter: BookSearchAdapter

    override fun onTapBookItem(bookVO: BookVO) {

    }

    companion object {

        @JvmStatic fun newIntent(context: Context?): Intent {
            val intent = Intent(context, BookSearchActivity::class.java)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_search)
        BookModel.getsObjectInstance().loadBook()


        mBookSearchAdapter = BookSearchAdapter(this, this)
        rvBookSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvBookSearch.adapter = mBookSearchAdapter








    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onQuestionLoaded(event: DataEvents.BookLoadedEvent) {
        Log.d("TAG", "BOOK LIST SEARCH"+event.bookList.size)
        mBookSearchAdapter.setNewData(event.bookList as MutableList<BookVO>)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }
}
