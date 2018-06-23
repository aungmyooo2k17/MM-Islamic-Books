package org.m2cs.mmislamicbooks.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnRenderListener
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.android.synthetic.main.activity_pdf_reader.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.R.id.pdfView
import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import java.io.File

class PdfReaderActivity : AppCompatActivity(), OnPageChangeListener {

    var pageIndex: Int = 0


    companion object {

        private val INTENT_USER_ID = "user_id"
        lateinit var pdfDownload: DownloadVO


        fun newIntent(context: Context, pdfPath: String): Intent {
            val intent = Intent(context, PdfReaderActivity::class.java)
            intent.putExtra(INTENT_USER_ID,pdfPath)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_reader)


        val pdfPath: String = intent.getStringExtra(INTENT_USER_ID)


        if (savedInstanceState != null) {
            pageIndex = savedInstanceState?.getInt("pageIndex")!!

        }



        pdfView.fromFile(File(pdfPath))
                .enableDoubletap(true)
                .defaultPage(pageIndex)
                .onPageChange(this)
                .spacing(10)
                .pageFitPolicy(FitPolicy.WIDTH)
                .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {

        pageIndex = page;
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("pageIndex", pageIndex)
    }


}
