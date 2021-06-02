@file:Suppress("DEPRECATION")

package com.cals.getloc.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.cals.getloc.R

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var  handler: Handler
    private val time = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashScreenActivity, onBoardingActivity::class.java)
            startActivity(intent)
            finish()
        }, time)
    }
}