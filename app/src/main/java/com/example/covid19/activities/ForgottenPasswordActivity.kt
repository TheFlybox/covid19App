package com.example.covid19.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covid19.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgotten_password.*

class ForgottenPasswordActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotten_password)
        mAuth = FirebaseAuth.getInstance()

        forgotten_button.setOnClickListener {
            mAuth?.sendPasswordResetEmail(forgotten_email.text.toString())
                ?.addOnCompleteListener {
                    Toast.makeText(
                        this,
                        "Check the email to change your forgotten password!",
                        Toast.LENGTH_LONG
                    ).show()
                }
        }
    }
}