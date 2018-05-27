package org.m2cs.mmislamicbooks.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.example.soe_than.pdftesting.utilities.Utils
import kotlinx.android.synthetic.main.activity_main.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.fragment.AuthorFragment
import org.m2cs.mmislamicbooks.fragment.CategoryFragment
import org.m2cs.mmislamicbooks.fragment.HomeFragment
import org.m2cs.mmislamicbooks.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        openFragment(HomeFragment.newInstance())

        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        Utils.disableShiftMode(navigationView)
    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = "Home"
                val homeFragment = HomeFragment.newInstance()
                openFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_category -> {
                toolbar.title = "Category"
                val categoryFragment = CategoryFragment.newInstance()
                openFragment(categoryFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_author -> {
                toolbar.title = "Author"
                val authorFragment = AuthorFragment.newInstance()
                openFragment(authorFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                toolbar.title = "Profile"
                val profileFragment = ProfileFragment.newInstance()
                openFragment(profileFragment)
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

}
