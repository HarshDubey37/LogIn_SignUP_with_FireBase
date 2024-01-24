package com.example.loginregisterwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.NonNull
import com.example.loginregisterwithfirebase.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var emailTextView:EditText
    private lateinit var passwordTextView:EditText
    private lateinit var  Bttn: Button;
    private lateinit var  progressbar: ProgressBar
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
                // taking FirebaseAuth instance
                mAuth = FirebaseAuth.getInstance();

                // initialising all views through id defined above
                emailTextView = binding.EmailTV;
                passwordTextView =binding.Password
                Bttn = binding.button
                progressbar = binding.prgbar

                // Set on Click Listener on Registration button
                Bttn.setOnClickListener {
                    registerNewUser()
                }
            }

           fun  registerNewUser() {
               // show the visibility of progress bar to show loading
               progressbar.setVisibility(View.VISIBLE);
               // Take the value of two edit texts in Strings
               val email = emailTextView.text.toString();
               val password = passwordTextView.text.toString();
               // Validations for input email and password
               if (TextUtils.isEmpty(email)) {
                   Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
                   return;
               }
               if (TextUtils.isEmpty(password)) {
                   Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
                   return;
               }
               // create new user or register new user
               mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener {
                       if (it.isSuccessful) {
                           Toast.makeText(applicationContext, "Registration successful!", Toast.LENGTH_LONG).show();
                           progressbar.setVisibility(View.GONE);
                           Log.d("Tag","$password")
                           var intent = Intent(this, LogInActivity::class.java)
                           startActivity(intent);
                       } else {
                           Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                           progressbar.setVisibility(View.GONE);
                       }
                   }
           }
                        }