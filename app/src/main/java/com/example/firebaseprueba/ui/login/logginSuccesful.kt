package com.example.firebaseprueba.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseprueba.databinding.ActivityLogginSuccesfulBinding
import com.example.firebaseprueba.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class logginSuccesful : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLogginSuccesfulBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        binding = ActivityLogginSuccesfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var username = auth.currentUser?.email;

        binding.currentUser.text = username;

        binding.logoutButton.setOnClickListener{
            auth.signOut();
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}