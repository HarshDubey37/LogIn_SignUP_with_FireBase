package com.example.loginregisterwithfirebase

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginregisterwithfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

binding.logout.setOnClickListener {
    sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("login", false)
        .apply()
    startActivity(Intent(this,LogInActivity::class.java))
    }

    }
}