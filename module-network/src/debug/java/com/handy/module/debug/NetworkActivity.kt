package com.handy.module.debug

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.handy.module.network.databinding.ActivityMainBinding
import com.handy.module.network.databinding.ActivityWidgetBinding
import com.handy.module.utils.LogUtils

/**
 * @author: handy
 */
open class NetworkActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "NetworkActivityPage"
        fun intentStart(context: Context) {
            val intent = Intent(context, NetworkActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        LogUtils.d(TAG,"onCreate mainBinding:${mainBinding.root}")
        val widgetBinding = ActivityWidgetBinding.bind(mainBinding.topMainLayer)

        //LogUtils.d(TAG,"onCreate widgetBinding:${widgetBinding.root}")

        /*NetCore.newCall(NetRequest())
            .flowFetch(String::class.java)
            .collectResult {
                LogUtils.d(TAG,"collectResult :$it")
            }*/
    }

}