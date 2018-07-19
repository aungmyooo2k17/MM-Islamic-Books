package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.mancj.slideup.SlideUp
import kotlinx.android.synthetic.main.activity_book_search.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.BookSearchAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.events.DataEvents
import org.m2cs.mmislamicbooks.models.BookModel
import android.text.Editable
import android.text.TextWatcher



class BookSearchActivity : AppCompatActivity(), BooksItemDelegate {


    lateinit var mBookSearchAdapter: BookSearchAdapter
    lateinit var slideUp: SlideUp
    private var filterbookList: ArrayList<BookVO> = ArrayList<BookVO>()
    private var bookList: ArrayList<BookVO> = ArrayList<BookVO>()



    override fun onTapBookItem(bookVO: BookVO) {

    }

    companion object {

        @JvmStatic
        fun newIntent(context: Context?): Intent {
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

        iv_search_close_btn.setOnClickListener(View.OnClickListener {
          

            finish()


        })

        editText.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(cs: CharSequence, arg1: Int, arg2: Int, arg3: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, arg1: Int, arg2: Int,
                                           arg3: Int) {
                if (s.toString().length === 0) {

                } else {
                    for (i in 0 until bookList.size) {
                        if (bookList.get(i).bookName.contains(s.toString().toLowerCase())) {
                            filterbookList.add(bookList.get(i))
                        }
                    }
                    mBookSearchAdapter.setNewData(filterbookList)
                }

            }

            override fun afterTextChanged(arg0: Editable) {
                // TODO Auto-generated method stub
            }
        })




    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onQuestionLoaded(event: DataEvents.BookLoadedEvent) {
        Log.d("TAG", "BOOK LIST SEARCH" + event.bookList.size)
        mBookSearchAdapter.setNewData(event.bookList as MutableList<BookVO>)

        for(bookVO: BookVO in event.bookList){
            bookList.add(bookVO)
        }

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }

//    private fun filter(text: String) {
//        //new array list that will hold the filtered data
////        val filterdNames = ArrayList()
//
//        //looping through existing elements
//        for (bookVO:BookVO in bookList) {
//            //if the
//            // existing elements contains the search input
//            if (bookVO.bookName.contains(text.toLowerCase())) {
//                //adding the element to filtered list
//                filterbookList.add(bookVO)
//            }
//        }
//
//        //calling a method of the adapter class and passing the filtered list
//        mBookSearchAdapter.setNewData(filterbookList)
//    }



}
