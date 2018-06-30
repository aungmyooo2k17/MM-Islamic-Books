package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.style.ClickableSpan
import android.view.View
import kotlinx.android.synthetic.main.activity_about.*
import org.m2cs.mmislamicbooks.R
import android.content.Intent
import android.net.Uri
import android.content.pm.PackageManager
import android.util.Log


class AboutActivity : AppCompatActivity() {
    var FACEBOOK_URL = ""
    var FACEBOOK_PAGE_ID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        rcCardview.setBackgroundResource(R.drawable.card_background)

        Log.i("FilesPath",filesDir.toString())

        imgClose.setOnClickListener(View.OnClickListener {
            finish()
        })
        info1.setOnClickListener(View.OnClickListener {
            val page = "http:\\m2cs.org"
            val webpage = Uri.parse(page)
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        })
        info2.setOnClickListener(View.OnClickListener {
            val facebookIntent = Intent(Intent.ACTION_VIEW)
            val facebookUrl = getFacebookPageURL(this)
            facebookIntent.data = Uri.parse(facebookUrl)
            startActivity(facebookIntent)
        })
        info3.setOnClickListener(View.OnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "m2cs786@gmail.com", null))
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        })
    }

    fun getFacebookPageURL(context: Context): String {
        val packageManager = context.getPackageManager()
        try {
            val versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode
            return if (versionCode >= 3002850) { //newer versions of fb app
                "fb://facewebmodal/f?href=$FACEBOOK_URL"
            } else { //older versions of fb app
                "fb://page/$FACEBOOK_PAGE_ID"
            }
        } catch (e: PackageManager.NameNotFoundException) {
            return FACEBOOK_URL //normal web url
        }

    }

}
