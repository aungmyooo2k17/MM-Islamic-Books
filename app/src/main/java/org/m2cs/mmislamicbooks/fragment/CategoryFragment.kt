package org.m2cs.mmislamicbooks.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.CategoryFragAdapter
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.model.Books
import org.m2cs.mmislamicbooks.model.Category

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
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Quran"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Hadith"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Aqeedah & Tawheed"))
        categories.add(Category(R.drawable.ic_muslim_praying,12,"Books on Salah (Prayers)"))
        categories.add(Category(R.drawable.ic_islamic_ramadan_,12,"Books on Ramadan"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Zakah (Charity)"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Hajj (Pilrimage)"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Angels & Books on Jinn (Spirits)"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Akhirah (The Hereafter)"))
        categories.add(Category(R.drawable.ic_allah_word,12,"Books in Arabic & Arabic Courses"))
        categories.add(Category(R.drawable.ic_islam,12,"Books on Islamic Belief"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Biographies"))
        categories.add(Category(R.drawable.ic_baby,12,"Books on Children"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on The Companions of The Prophet"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Comparative Religion"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Islamic Current Affairs"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Dawah (Propagation)"))
        categories.add(Category(R.drawable.ic_family,12,"Books on The Family"))
        categories.add(Category(R.drawable.ic_reading_quran,12,"Books on Fiqh (Jurisprudence)"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on Islamic Knowledge"))
        categories.add(Category(R.drawable.ic_interlocking_rings,12,"Books on Marriage"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on Islamic History"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on Seerah"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on The Shari’ah of Allah"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on Suplications"))
        categories.add(Category(R.drawable.ic_thought,12,"Books on Tazkiya"))
        categories.add(Category(R.drawable.ic_muslim_woman_praying,12,"Books on Women"))
        categories.add(Category(R.drawable.ic_thought,12,"Books for Non-Muslims"))
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
