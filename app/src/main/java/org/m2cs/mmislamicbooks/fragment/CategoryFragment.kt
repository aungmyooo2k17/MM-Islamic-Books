package org.m2cs.mmislamicbooks.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_category.view.*

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.CategoryFragAdapter
import org.m2cs.mmislamicbooks.data.model.Category

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CategoryFragment : BaseFragment() {

    val categories: ArrayList<Category> = ArrayList()
    private lateinit var categoryAdapter: CategoryFragAdapter
    companion object {
        fun newInstance():CategoryFragment= CategoryFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_category, container, false)

        initializedArray()
        setUpRecyclerView(view)

        return view
    }

    private fun initializedArray()
    {
        categories.add(Category(12, "Book on Quran"))
        categories.add(Category(12, "Book on Hadith"))
        categories.add(Category(12, "Book on Aqeedah & Tawheed"))
        categories.add(Category(12, "Book on Salah (Prayers)"))
        categories.add(Category(12, "Book on Ramadan"))
        categories.add(Category(12, "Book on Zakah (Charity)"))
        categories.add(Category(12, "Book on Hajj (Pilrimage)"))
        categories.add(Category(12, "Book on Angels & Book on Jinn (Spirits)"))
        categories.add(Category(12, "Book on Akhirah (The Hereafter)"))
        categories.add(Category(12, "Book in Arabic & Arabic Courses"))
        categories.add(Category(12, "Book on Islamic Belief"))
        categories.add(Category(12, "Book on Biographies"))
        categories.add(Category(12, "Book on Children"))
        categories.add(Category(12, "Book on The Companions of The Prophet"))
        categories.add(Category(12, "Book on Comparative Religion"))
        categories.add(Category(12, "Book on Islamic Current Affairs"))
        categories.add(Category(12, "Book on Dawah (Propagation)"))
        categories.add(Category(12, "Book on The Family"))
        categories.add(Category(12, "Book on Fiqh (Jurisprudence)"))
        categories.add(Category(12, "Book on Islamic Knowledge"))
        categories.add(Category(12, "Book on Marriage"))
        categories.add(Category(12, "Book on Islamic History"))
        categories.add(Category(12, "Book on Seerah"))
        categories.add(Category(12, "Book on The Shari’ah of Allah"))
        categories.add(Category(12, "Book on Suplications"))
        categories.add(Category(12, "Book on Tazkiya"))
        categories.add(Category(12, "Book on Women"))
        categories.add(Category(12, "Book for Non-Muslims"))
    }

    private fun setUpRecyclerView(view:View)
    {
        view.recyclerViewCategory.layoutManager = LinearLayoutManager(activity)
        view.recyclerViewCategory.isNestedScrollingEnabled=true
        categoryAdapter = CategoryFragAdapter(context)
        categoryAdapter.replaceData(categories)
        view.recyclerViewCategory.adapter = categoryAdapter
    }


}
