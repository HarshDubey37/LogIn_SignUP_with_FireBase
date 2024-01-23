package com.example.loginregisterwithfirebase

import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.loginregisterwithfirebase.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var email:EditText
    private lateinit var pass:EditText
    private lateinit var pgr:ProgressBar
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var mauth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences=getSharedPreferences("login", MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        binding=ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email=binding.EmailTV
        pgr=binding.prgbar
        pass=binding.Password
        mauth=FirebaseAuth.getInstance()

        binding.Signup.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }


        binding.button.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        pgr.visibility= View.VISIBLE

        var emaill=email.text.toString()
        var pass=pass.text.toString()
        if (TextUtils.isEmpty(emaill)){
            Toast.makeText(this,"Please Enter Email..",Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password..",Toast.LENGTH_SHORT).show()
            return
        }

        mauth.signInWithEmailAndPassword(emaill,pass)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"Login Successfull..",Toast.LENGTH_SHORT).show()
                    pgr.visibility=View.GONE
                    val ed=sharedPreferences.edit().putBoolean("login",true)
                    ed.apply()
                    startActivity(Intent(this,MainActivity::class.java))
                }else{
                    Log.d("Tag","$pass")
                        Toast.makeText(this,"Login Failed..",Toast.LENGTH_SHORT).show()
                    pgr.visibility=View.GONE
                }
            }

    }
}