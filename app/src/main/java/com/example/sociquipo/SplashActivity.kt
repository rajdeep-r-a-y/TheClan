package com.example.sociquipo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        handler = Handler()
        handler.postDelayed({

            // Delay and Start Activity
            val intent = Intent(
                SplashActivity@this,
                SignInActivity::class.java
            )
            startActivity(intent)
            finish()
        } , 1000)


    }
}