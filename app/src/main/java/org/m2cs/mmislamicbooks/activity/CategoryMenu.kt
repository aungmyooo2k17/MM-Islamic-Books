package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_category_menu.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.data.model.Category

class CategoryMenu : AppCompatActivity() {


    companion object {
        val CATEGORY_KEY = "category_key"

        @JvmStatic fun getIntent(context: Context, category: Category): Intent {
            val intent = Intent(context, CategoryMenu::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_menu)

        setSupportActionBar(catmenuToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val category = intent.getParcelableExtra<Category>(CategoryMenu.CATEGORY_KEY)
//
//        catmenuToolbar.title=category.categoryTitle
//        catmenuToolbar.setTitleTextColor(resources.getColor(R.color.primaryText))
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId==android.R.id.home)
        {
            finish()
        }
        return true
    }
}
