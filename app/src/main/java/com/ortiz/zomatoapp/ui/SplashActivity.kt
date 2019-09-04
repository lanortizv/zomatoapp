package com.ortiz.zomatoapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ortiz.zomatoapp.R

class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME: Long = 1000

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val timer = object : Thread() {
            override fun run() {
                try {
                    sleep(SPLASH_TIME)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    val i = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
        timer.start()
    }
}
