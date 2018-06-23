package org.m2cs.mmislamicbooks.activity

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.adapter.HomeFragAdapter
import org.m2cs.mmislamicbooks.data.vo.BookVO
import org.m2cs.mmislamicbooks.data.vo.CategoryVO
import org.m2cs.mmislamicbooks.delegates.BooksItemDelegate
import org.m2cs.mmislamicbooks.model.Books
import org.m2cs.mmislamicbooks.models.BookModel
import org.m2cs.mmislamicbooks.models.CategoryModel
import org.m2cs.mmislamicbooks.utils.DownloadManagerPro
import org.m2cs.mmislamicbooks.utils.PreferencesUtils
import java.io.File
import android.content.pm.PackageManager
import android.Manifest.permission
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.support.v4.content.ContextCompat
import android.support.v4.app.ActivityCompat
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.database.ContentObserver
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import org.m2cs.mmislamicbooks.R.id.*
import org.m2cs.mmislamicbooks.database.DbHelper
import org.m2cs.mmislamicbooks.utils.DownloadUtils.getBookSize
import org.m2cs.mmislamicbooks.utils.DownloadUtils.getNotiPercent
import org.m2cs.mmislamicbooks.utils.DownloadUtils.isDownloading


class BookDetailActivity : AppCompatActivity(), BooksItemDelegate {


    val books: ArrayList<Books> = ArrayList()
    private lateinit var mHomeAdapter: HomeFragAdapter
    lateinit var categoryVO: CategoryVO
    lateinit var downloadManager: DownloadManager
    lateinit var downloadManagerPro: DownloadManagerPro

    var stringUrl: String = ""

    var PERMISSION_CODE: Int = 0
    lateinit var handler: Handler
    internal lateinit var downloadObserver: DownloadChangeObserver
//    internal lateinit var completeReceiver: CompleteReceiver


    init {
        mDatabse = DbHelper(this)
    }

    companion object {
        val BOOK_KEY = "book_key"
        lateinit var mDatabse: DbHelper
        var downloadId: Long = 0
        var KEY_NAME_DOWNLOAD_ID = ""
        lateinit var bookVO: BookVO
        lateinit var mFileName: String
        lateinit var mFilePath: String




        @JvmStatic
        fun newIntent(context: Context?, book: BookVO): Intent {
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra(BOOK_KEY, book.bookId)
            return intent
        }




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        ButterKnife.bind(this)

        val book = intent.getStringExtra(BOOK_KEY)
        bookVO = BookModel.getsObjectInstance().getBookById(book)!!



        tv_book_title_details.text = bookVO.bookName
        tv_book_author_detail.text = bookVO.authorId
        tv_book_desc_detail.text = bookVO.bookDesc
        stringUrl = bookVO.bookDownloadLink.toString()
        KEY_NAME_DOWNLOAD_ID = bookVO.bookId.toString()
        Glide.with(this).load(bookVO.bookDetailCover).into(iv_book_detail_cover)

        if (checkDownloadBook()) {
            Log.i("BookDetailCheck", "AlreadDown")
            btn_download.text = "Read"
        } else {
            btn_download.text = "Download"
        }


        rv_similar_book.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mHomeAdapter = HomeFragAdapter(this, this)
        rv_similar_book.adapter = mHomeAdapter
        rv_similar_book.isNestedScrollingEnabled = false
        rv_similar_book.setFocusable(false);
        mHomeAdapter.setNewData(BookModel.getsObjectInstance().getBookByCategoryId(bookVO.categoryId!!) as MutableList<BookVO>)
        handler = MyHandler(btn_cancel, btn_download, tv_download_percent, tv_download_size, btn_progress)
        downloadManager = this.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

        downloadManagerPro = DownloadManagerPro(downloadManager)

        initData()

        downloadObserver = DownloadChangeObserver();
//        completeReceiver = CompleteReceiver();
        /** register download success broadcast **/
//        registerReceiver(completeReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));


    }

    fun checkDownloadBook(): Boolean {
        var bookList = listOf<String>()
        bookList = mDatabse.getBookTitle()
        for (item in bookList) {
            if (item.equals(bookVO.bookName, true)) {
                Log.i("TAG!", item)
                return true

            }

        }
        return false
    }

    fun updateView() {
        val bytesAndStatus = downloadManagerPro.getBytesAndStatus(downloadId)
        handler.sendMessage(handler.obtainMessage(0, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]))
    }

    fun initData() {

        downloadId = PreferencesUtils.getLong(this, KEY_NAME_DOWNLOAD_ID);
        updateView();
        btn_download.setOnClickListener {
            if (checkDownloadBook()) {
                var intent = PdfReaderActivity.newIntent(this, mDatabse.getFilePath(bookVO.bookId.toInt()));
                startActivity(intent);
            } else if (!checkDownloadBook()) {
//                btn_download.setText("Download")
                if (checkPermission()) downloadFiles() else requestPermission()

            }

        }
        btn_cancel.setOnClickListener {
            downloadManager.remove(downloadId)
            updateView()
        }
    }

    fun downloadFiles() {
        val direct = File(Environment.getExternalStorageDirectory().toString() + "/${getString(R.string.app_name)}")
        if (!direct.exists()) {
            direct.mkdirs()
        }

        val uri = Uri.parse(stringUrl)
        val request = DownloadManager.Request(uri)
        mFileName = bookVO.bookName + ".pdf"
        mFilePath = Environment.getExternalStorageDirectory().absolutePath
        mFilePath += "/${getString(R.string.app_name)}/$mFileName"


        request.setTitle(bookVO.bookName)
                .setDescription("Downloading")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir("/MM Islamic Books", mFileName)
                .setVisibleInDownloadsUi(true)
        downloadId = downloadManager.enqueue(request)
        PreferencesUtils.putLong(this, KEY_NAME_DOWNLOAD_ID, downloadId);

    }

    fun requestPermission() {

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_CODE)

    }

    fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        return if (result == PackageManager.PERMISSION_GRANTED) true else false
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                downloadFiles()
                btn_cancel.visibility = View.VISIBLE

            } else {
                Toast.makeText(applicationContext, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    internal inner class DownloadChangeObserver : ContentObserver(handler) {

        override fun onChange(selfChange: Boolean) {
            updateView()

        }

    }
//
//    internal inner class CompleteReceiver : BroadcastReceiver() {
//
//        override fun onReceive(context: Context, intent: Intent) {
//
//            val completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
//            if (completeDownloadId == downloadId) {
//                updateView()
//                Log.i("BookDetailBroadcast","$downloadId")
//                mDatabse.addBook(bookVO, mFilePath)
//                PreferencesUtils.removeValue(this@BookDetailActivity, KEY_NAME_DOWNLOAD_ID)
//
//
//            }
//        }
//    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return true
    }


    override fun onTapBookItem(bookVO: BookVO) {
        val intent: Intent = BookDetailActivity.newIntent(applicationContext, bookVO)
        startActivity(intent)
    }

    private class MyHandler(var mCancelButton: ImageView, var mDownloadButton: Button,
                            var mDownloadPercent: TextView, var mDownloadSize: TextView,
                            var mProgressbar: ProgressBar) : Handler() {

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            when (msg!!.what) {
                0 -> {
                    val status = msg!!.obj as Int
                    if (isDownloading(status)) {
                        mProgressbar.setVisibility(View.VISIBLE)
                        mProgressbar.setMax(0)
                        mProgressbar.setProgress(0)
                        mDownloadButton.setVisibility(View.GONE)
                        mDownloadSize.setVisibility(View.VISIBLE)
                        mDownloadPercent.setVisibility(View.VISIBLE)
                        mCancelButton.setVisibility(View.VISIBLE)

                        if (msg.arg2 < 0) {
                            mProgressbar.setIndeterminate(true)
                            mDownloadPercent.setText("0%")
                            mDownloadSize.setText("0M/0M")
                        } else {
                            mProgressbar.setIndeterminate(false)
                            mProgressbar.setMax(msg.arg2)
                            mProgressbar.setProgress(msg.arg1)
                            mDownloadPercent.setText(getNotiPercent(msg.arg1.toLong(), msg.arg2.toLong()))
                            mDownloadSize.setText("${getBookSize(msg.arg1.toLong())}/${getBookSize(msg.arg2.toLong())}")
                        }
                    } else {
                        mProgressbar.setVisibility(View.GONE)
                        mProgressbar.setMax(0)
                        mProgressbar.setProgress(0)
                        mDownloadButton.setVisibility(View.VISIBLE)
                        mDownloadSize.setVisibility(View.GONE)
                        mDownloadPercent.setVisibility(View.GONE)
                        mCancelButton.setVisibility(View.GONE)

                        if (status == DownloadManager.STATUS_FAILED) {
                            mDownloadButton.setText("Download")

                        } else if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            mDownloadButton.setText("Read")
//
                        }
//                        else {
//                            mDownloadButton.setText("Download")
//                        }
                    }
                }
            }


        }


    }


    override fun onResume() {
        super.onResume()
        Log.i("BookDetail", "onResume")
        contentResolver.registerContentObserver(DownloadManagerPro.CONTENT_URI, true, downloadObserver);
        updateView();
    }

    //
//
    override fun onPause() {
        super.onPause();
        contentResolver.unregisterContentObserver(downloadObserver);
    }


    override fun onDestroy() {
        super.onDestroy();
//        unregisterReceiver(completeReceiver);
    }


}
