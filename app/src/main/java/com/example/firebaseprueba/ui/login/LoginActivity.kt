package com.example.firebaseprueba.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.example.firebaseprueba.databinding.ActivityLoginBinding

import com.example.firebaseprueba.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val register = binding.register

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)


        login.setOnClickListener{
            if(username.text.isNotEmpty() && password.text.isNotEmpty()){
                auth.signInWithEmailAndPassword(username.text.toString(),password.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Log.d("login","usuario logueado")

                            var intent = Intent(this, logginSuccesful::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("login","register error", task.exception)

                        }
                    }
            }
        }

        register.setOnClickListener{
            if(username.text.isNotEmpty() && password.text.isNotEmpty()){
                auth.createUserWithEmailAndPassword(username.text.toString(),password.text.toString())
                    .addOnCompleteListener(this){ task ->
                        if(task.isSuccessful){
                            Log.d("login","usuario registrado")

                            var intent = Intent(this, logginSuccesful::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("login","loginerror", task.exception)

                        }
                    }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if( FirebaseAuth.getInstance().currentUser != null ){
            Toast.makeText(this, "usuario existente", Toast.LENGTH_SHORT)
            var intent = Intent(this, logginSuccesful::class.java)
            startActivity(intent)
        }
    }

}