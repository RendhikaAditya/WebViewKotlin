package com.kabarnagari

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScrean : AppCompatActivity() {
    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefManager:PrefManager=PrefManager(this)
        setContentView(R.layout.activity_splash_screan)
            handler = Handler()
            handler.postDelayed({
                if (prefManager.getValueInt("id").equals(1)){
                    val intent = Intent(this, WebView::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    finish()
                    startActivity(intent)
                    finish()
                }else {
                    val intent = Intent(this, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    finish()
                    startActivity(intent)
                    finish()
                }
            }, 2000)
    }
}
