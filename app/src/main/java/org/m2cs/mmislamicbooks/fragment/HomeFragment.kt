package org.m2cs.mmislamicbooks.fragment


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soe_than.pdftesting.utilities.Utility
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.greenrobot.eventbus.EventBus

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.model.Books
import org.greenrobot.eventbus.ThreadMode
import org.greenrobot.eventbus.Subscribe
import org.m2cs.mmislamicbooks.activity.BookDetailActivity
import org.m2cs.mmislamicbooks.activity.BookSearchActivity
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.events.DataEvents
import org.m2cs.mmislamicbooks.models.BookModel


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
        var view: View = inflater.inflate(R.layout.fragment_home, container,
                false)

        var isConnected = Utility.checkConnectivity(context!!)


        if (isConnected) {
            getConnected(view)
        } else {
            noConnection(view)

        }

        view.layoutRetry.setOnClickListener({
            recheckInternet()
        })




        initializedArray()


        view.edt_search.setOnClickListener(View.OnClickListener {
            var intent: Intent = BookSearchActivity.newIntent(activity)
            startActivity(intent)
        })



        return view
    }

    private fun initializedArray() {
        for (i in 1..15) {
            books.add(Books())
        }

    }

    fun getConnected(view: View) {

        Log.i("HomeFragCheck", view.recyclerView.visibility.toString())
        BookModel.getsObjectInstance().loadBook()
        view.layoutRetry.visibility = View.GONE
        view.recyclerView.visibility = View.VISIBLE



        setUpRecyclerView(view)

    }

    fun noConnection(view: View) {
        view.recyclerView.visibility = View.GONE
        view.layoutRetry.visibility = View.VISIBLE
//        view.internetStatus.visibility = View.VISIBLE
//        view.btn_retry.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView(view: View) {
        view.recyclerView.layoutManager = GridLayoutManager(activity, 2)
        view.recyclerView.isNestedScrollingEnabled = false
        mHomeAdapter = HomeFragAdapter(context, this)
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

    fun recheckInternet() {
        if (Utility.checkConnectivity(context!!)) {
            getConnected(view!!)
            Log.i("HomeCheck", "onConnected")
        } else {

            noConnection(view!!)
            Log.i("HomeCheck", "No")
        }
    }

}
