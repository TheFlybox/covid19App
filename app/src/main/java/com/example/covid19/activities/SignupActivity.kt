package com.example.covid19.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.covid19.MainActivity
import com.example.covid19.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

class SignupActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var ref: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()
        ref = FirebaseFirestore.getInstance()

        signup_button.setOnClickListener {
            signUp(
                signup_firstname.text.toString(),
                signup_lastname.text.toString(),
                signup_email.text.toString(),
                signup_password.text.toString()
            )
        }

        signup_login.setOnClickListener {goToLogin()}
    }
    private fun signUp(fname: String, lname: String, email: String, password: String){
        var user: FirebaseUser? = null
        mAuth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {
            if(it.isSuccessful){
                user = mAuth!!.currentUser
                val dataUser: HashMap<String, String> = HashMap()
                dataUser.put("id", user!!.uid)
                dataUser.put("firstname", fname)
                dataUser.put("lastname", lname)
                dataUser.put("email", email)
                dataUser.put("password", password)
                ref?.collection("users")?.add(dataUser)?.addOnSuccessListener() {
                    Toast.makeText(this, "User sign up successfully!", Toast.LENGTH_LONG).show()
                    goToMain()
                }
            }
        }
    }

    private fun goToMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}