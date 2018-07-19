package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_category_menu.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.CategoryDetailAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.events.DataEvents
import org.m2cs.mmislamicbooks.models.BookModel

class CategoryDetailActivity : AppCompatActivity(), BooksItemDelegate {



    var catId = ""
    var catName = ""
    lateinit var mCategoryDetailAdapter: CategoryDetailAdapter

    companion object {
        val CATEGORY_KEY = "category_key"
        val CATEGORY_NAME = "category_name"

        @JvmStatic fun getIntent(context: Context, categoryId: String, categoryName: String): Intent {
            val intent = Intent(context, CategoryDetailActivity::class.java)
            intent.putExtra(CATEGORY_KEY, categoryId)
            intent.putExtra(CATEGORY_NAME, categoryName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_menu)

        catId = intent.getStringExtra(CATEGORY_KEY)
        catName = intent.getStringExtra(CATEGORY_NAME)

        Log.d("TAG", "INTETNT"+catId)

        BookModel.getsObjectInstance().loadBook()

        rvCategoryDetailBookList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mCategoryDetailAdapter = CategoryDetailAdapter(this, this)
        rvCategoryDetailBookList.adapter = mCategoryDetailAdapter



        setSupportActionBar(catmenuToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title =  catName
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home)
        {
            finish()
        }
        return true
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onQuestionLoaded(event: DataEvents.BookLoadedEvent) {
        Log.d("CategoryDetailActivity", "hell thread")

        mCategoryDetailAdapter.setNewData(BookModel.getsObjectInstance().getBookByCategoryId(catId, event.bookList) as MutableList<BookVO>)


    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }



    override fun onTapBookItem(bookVO: BookVO) {
        val intent: Intent = BookDetailActivity.newIntent(this, bookVO)
        startActivity(intent)
    }
}
