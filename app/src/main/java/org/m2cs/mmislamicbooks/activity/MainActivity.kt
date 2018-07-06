package org.m2cs.mmislamicbooks.activity

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.soe_than.pdftesting.utilities.Utility
import kotlinx.android.synthetic.main.activity_main.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.delegates.BookMainDelegate
import org.m2cs.mmislamicbooks.fragment.*
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.models.CategoryModel
import android.view.View
import org.m2cs.mmislamicbooks.viewmodels.BookViewModel


class MainActivity : AppCompatActivity(), BookMainDelegate {

    private lateinit var bookViewModel: BookViewModel
    private lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        openFragment(HomeFragment.newInstance())

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Utility.disableShiftMode(navigationView)
        BookModel.getsObjectInstance().loadBook()
        CategoryModel.getObjectInstance().loadCategory()
        toAbout.setOnClickListener(View.OnClickListener {
            intent = Intent(this,AboutActivity::class.java)
            startActivity(intent)
        })
//        AuthorModel.getsObjectInstance().loadAuthor()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                BookModel.getsObjectInstance().loadBook()
                tv_main_title.text = "Home"
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_category -> {
                tv_main_title.text = "Category"
                val categoryFragment = CategoryFragment.newInstance()
                openFragment(categoryFragment)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_download -> {
                tv_main_title.text = "Downloads"
                val downloadsFragment = DownloadsFragment.newInstance()
                openFragment(downloadsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onTapBook(bookVO: BookVO) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        } else {
            // Swap without transition
        }
        val intent: Intent = BookDetailActivity.newIntent(this, bookVO)
//        val optionsCompat: ActivityOptionsCompat = makeSceneTransitionAnimation(this, iv_book_detail_cover, "bookcover")
        startActivity(intent)
    }

}
