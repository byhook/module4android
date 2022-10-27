package com.handy.module4android

import com.handy.module.debug.NetworkActivity.Companion.intentStart
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.handy.module4android.R
import com.handy.module.debug.NetworkActivity
import com.handy.module.pages.ImmersionActivity
import com.handy.module4android.PermissionActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainCorePage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNetworkClick(view: View?) {
        intentStart(this)
    }

    fun onImmersionClick(view: View?) {
        ImmersionActivity.intentStart(this)
    }

    fun onPermissionClick(view: View?) {
        PermissionActivity.intentStart(this)
    }

}