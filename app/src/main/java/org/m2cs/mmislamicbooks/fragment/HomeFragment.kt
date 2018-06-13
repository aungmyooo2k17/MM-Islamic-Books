package org.m2cs.mmislamicbooks.fragment


import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_content_view.*
import org.greenrobot.eventbus.EventBus

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.model.Books
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import org.m2cs.mmislamicbooks.R.id.iv_book_detail_cover
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.events.DataEvents


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment(), BooksItemDelegate {


    val books: ArrayList<Books> = ArrayList()

    public lateinit var mHomeAdapter: HomeFragAdapter
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container,
                false)
        initializedArray()

        setUpRecyclerView(view)



        return view
    }

    private fun initializedArray()
    {
        for (i in 1..15)
        {
            books.add(Books())
        }

    }

    private fun setUpRecyclerView(view:View)
    {
        view.recyclerView.layoutManager = GridLayoutManager(activity,2)
        view.recyclerView.isNestedScrollingEnabled=false
        mHomeAdapter = HomeFragAdapter(context, this)
//        mHomeAdapter.setNewData(App.globalBookList as MutableList<BookVO>)
        view.recyclerView.adapter = mHomeAdapter
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onQuestionLoaded(event: DataEvents.BookLoadedEvent) {
        mHomeAdapter.setNewData(event.bookList as MutableList<BookVO>)
    }


    override fun onTapBookItem(bookVO: BookVO) {

        val intent: Intent = BookDetailActivity.newIntent(context, bookVO)
        context!!.startActivity(intent)
    }

}
