package com.vidora.app

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebChromeClient
import android.widget.Toast
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform

class MainActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        setContentView(webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        try {
            if (!Python.isStarted()) {
                Python.start(AndroidPlatform(this))
            }

            val python = Python.getInstance()
            val module = python.getModule("main")
            module.callAttr("start_server")

            Handler(Looper.getMainLooper()).postDelayed({
                webView.loadUrl("http://127.0.0.1:8080")
            }, 1000)

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "خطأ في تشغيل Vidora: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
