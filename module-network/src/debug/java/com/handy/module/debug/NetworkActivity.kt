package com.handy.module.debug

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.handy.module.network.NetCore
import com.handy.module.network.databinding.ActivityMainNetworkBinding
import com.handy.module.network.ext.catchResult
import com.handy.module.network.ext.collectResult
import com.handy.module.network.request.NetRequest
import com.handy.module.utils.LogUtils

/**
 * @author: handy
 */
open class NetworkActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "NetworkActivityPage"

        @JvmStatic
        fun intentStart(context: Context) {
            val intent = Intent(context, NetworkActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var mainBinding: ActivityMainNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainNetworkBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.btnGetRequest.setOnClickListener(::handleBtnGetRequestClick)
    }

    private fun handleBtnGetRequestClick(view: View) {
        NetCore.newCall("https://www.baidu.com", NetRequest())
            .flowFetch(String::class.java)
            .catchResult {
                LogUtils.d(TAG, "catchResult :$it")
            }
            .collectResult {
                LogUtils.d(TAG, "collectResult :$it")
            }
    }

}