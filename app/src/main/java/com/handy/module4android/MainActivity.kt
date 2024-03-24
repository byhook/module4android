package com.handy.module4android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.handy.module.pages.ImmersionActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainCorePage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onNetworkClick(view: View?) {

    }

    fun onImmersionClick(view: View?) {
        ImmersionActivity.intentStart(this)
    }

    fun onPermissionClick(view: View?) {
        PermissionActivity.intentStart(this)
    }

}