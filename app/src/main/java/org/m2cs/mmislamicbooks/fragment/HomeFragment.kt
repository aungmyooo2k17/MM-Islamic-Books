package org.m2cs.mmislamicbooks.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.model.Books

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {

    val books: ArrayList<Books> = ArrayList()

    private lateinit var mHomeAdapter: HomeFragAdapter
    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_home, container,
                false)
        initializedArray()
        view.recyclerView.layoutManager = GridLayoutManager(activity,2) as RecyclerView.LayoutManager?
        view.recyclerView.isNestedScrollingEnabled=false
        mHomeAdapter = HomeFragAdapter(context)
        mHomeAdapter.replaceData(books)
        view.recyclerView.adapter = mHomeAdapter


        return view
    }

    private fun initializedArray()
    {
        for (i in 1..15)
        {
            books.add(Books())
        }

    }


}
