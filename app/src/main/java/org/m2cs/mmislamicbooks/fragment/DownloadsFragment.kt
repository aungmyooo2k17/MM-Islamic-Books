package org.m2cs.mmislamicbooks.fragment


import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.soe_than.pdftesting.utilities.Utility
import kotlinx.android.synthetic.main.fragment_downloads.view.*

import org.m2cs.mmislamicbooks.R
import org.m2cs.mmislamicbooks.activity.PdfReaderActivity
import org.m2cs.mmislamicbooks.adapter.DownloadAdapter
import org.m2cs.mmislamicbooks.data.vo.DownloadVO
import org.m2cs.mmislamicbooks.database.DbHelper
import org.m2cs.mmislamicbooks.delegates.DownloadItemDelegate
import java.io.File

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DownloadsFragment : BaseFragment(), DownloadItemDelegate {
    override fun onTapRead(downloadBook: DownloadVO) {
        var intent = PdfReaderActivity.newIntent(context!!, downloadBook.mFilePath);
        startActivity(intent);
    }

    lateinit var mDatabase: DbHelper
    var fileNameList = arrayListOf<String>()
    var mdownloadVo = null

    override fun onTapDelete(downloadVo: DownloadVO, adapterPosition: Int) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Alert")
        builder.setMessage("Are you want to delete?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { dialog, which ->
            val file = File(downloadVo.mFilePath)
            file.delete()

            mDatabase.removeItemWithId(downloadVo.downloadBookId)
            downloadAdapter.notifyItemRemoved(adapterPosition)
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()

        }
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }


    lateinit var downloadAdapter: DownloadAdapter

    companion object {
        fun newInstance(): DownloadsFragment = DownloadsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        observer.startWatching()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_downloads, container, false)
        setUpRecyclerView(view)
        mDatabase = DbHelper(context!!)
        fileNameList = Utility.getListFile()
        mDatabase.getBookTitle()
//        if (fileNameList.size != 0) {
//            checkFilePath(fileNameList, mDatabase.getBookTitle())
//
//        }
        return view
    }


    private fun setUpRecyclerView(view: View) {
        view.downloadRecyclerView.layoutManager = LinearLayoutManager(context)
        downloadAdapter = DownloadAdapter(context, this)
        view.downloadRecyclerView.adapter = downloadAdapter
    }


//    fun checkFilePath(fileList: ArrayList<String>, downloadList: ArrayList<String>) {
//        for (fileItem in fileList) {
//            for (downloadItem in downloadList) {
//                if (downloadItem.equals(fileItem)) {
//                    downloadAdapter.removeItemFromDb()
//
//                }
//            }
//        }
//
//    }


}
