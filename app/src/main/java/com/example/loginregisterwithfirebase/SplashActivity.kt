package com.example.loginregisterwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val sharedPreferences=getSharedPreferences("login", MODE_PRIVATE)
            if (sharedPreferences.getBoolean("login",false)){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                startActivity(Intent(this,LogInActivity::class.java))

            }
            finish()
        },1800)

    }
}