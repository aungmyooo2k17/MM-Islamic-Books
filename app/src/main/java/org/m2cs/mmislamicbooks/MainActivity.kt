package org.m2cs.mmislamicbooks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.m2cs.mmislamicbooks.models.AuthorModel
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.models.CategoryModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BookModel.getsObjectInstance().loadPerson()
        CategoryModel.getObjectInstance().loadCategory()
        AuthorModel.getsObjectInstance().loadAuthor()

    }
}
