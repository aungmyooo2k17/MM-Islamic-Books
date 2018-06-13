package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.CategoryVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.model.Books
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.models.CategoryModel

class BookDetailActivity : AppCompatActivity(), BooksItemDelegate {


    val books: ArrayList<Books> = ArrayList()
    private lateinit var mHomeAdapter: HomeFragAdapter
    lateinit var bookVO: BookVO
    lateinit var categoryVO: CategoryVO

    companion object {
        val BOOK_KEY = "book_key"

        @JvmStatic fun newIntent(context: Context?, book: BookVO): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(BOOK_KEY,book.bookId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val book = intent.getStringExtra(BOOK_KEY)
        bookVO = BookModel.getsObjectInstance().getBookById(book)!!



        tv_book_title_details.text = bookVO.bookName
        tv_book_author_detail.text = bookVO.authorId
        tv_book_desc_detail.text = bookVO.bookDesc
        Glide.with(this).load(bookVO.bookDetailCover).into(iv_book_detail_cover)

        rv_similar_book.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mHomeAdapter = HomeFragAdapter(this,this)
        rv_similar_book.adapter = mHomeAdapter
        rv_similar_book.isNestedScrollingEnabled = false
        rv_similar_book.setFocusable(false);
        mHomeAdapter.setNewData(BookModel.getsObjectInstance().getBookByCategoryId(bookVO.categoryId!!) as MutableList<BookVO>)


    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home)
        {
            finish()
        }
        return true
    }



    override fun onTapBookItem(bookVO: BookVO) {
        val intent: Intent = BookDetailActivity.newIntent(applicationContext, bookVO)
        startActivity(intent)
    }
}
