package com.example.covid19.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.covid19.MainActivity
import com.example.covid19.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        if(mAuth?.currentUser != null) {
            goToMain()
        }

        login_button.setOnClickListener {
            login(login_email.text.toString(), login_password.text.toString())
        }

        login_signup.setOnClickListener {
            goToSignup()
        }

        login_forgotpassword.setOnClickListener {
            goToForgottenPassword()
        }
    }

    private fun login(email: String, password: String){
        mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(OnCompleteListener {
            if(it.isSuccessful){
                goToMain()
            }
        })
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun goToSignup(){
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
    private fun goToForgottenPassword(){
        val intent = Intent(this, ForgottenPasswordActivity::class.java)
        startActivity(intent)
    }
}