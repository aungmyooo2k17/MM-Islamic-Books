package com.example.soe_than.pdftesting.utilities

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.support.design.widget.BottomNavigationView
import android.support.design.internal.BottomNavigationItemView
import java.lang.reflect.AccessibleObject.setAccessible
import java.lang.reflect.Array.setBoolean
import android.support.design.internal.BottomNavigationMenuView
import android.util.Log
import android.util.TypedValue
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Environment
import java.io.File
import java.io.StreamTokenizer


class Utility {

    companion object {
        @SuppressLint("RestrictedApi")
        fun disableShiftMode(view: BottomNavigationView) {
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView
                    item.setShiftingMode(false)
                    // set once again checked value, so view will be updated
                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
                Log.e("BNVHelper", "Unable to get shift mode field", e)
            } catch (e: IllegalAccessException) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e)
            }


        }


        fun checkConnectivity(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo


            if (netInfo != null && netInfo.isConnectedOrConnecting) {
                return true
            } else {
                return false
            }
        }


        fun getListFile(): ArrayList<String> {
            var filesList = arrayListOf<String>()
            var root = Environment.getExternalStorageDirectory().toString()
            var file = File(root, "/MM Islamic Books")
            var fileList = file.listFiles()

            for (i in fileList) {
                var array = i.name.split(".p")
                filesList.add(array.get(0))
            }

            return filesList

        }


    }


}

