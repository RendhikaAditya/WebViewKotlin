package com.kabarnagari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView.loadUrl("https://kabarnagari.com")
        webView.webViewClient= WebViewClient()
        webView.webChromeClient= WebChromeClient()
        webView.settings.javaScriptEnabled = true
        webView.settings.builtInZoomControls = false
        webView.settings.displayZoomControls = false


    }

    override fun onBackPressed() {
        finish()
    }
}
