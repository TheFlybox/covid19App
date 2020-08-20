package com.example.covid19.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.covid19.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.view.*


class ProfileFragment : Fragment() {

    private var ref: FirebaseFirestore? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        ref?.collection("users")?.get()?.addOnCompleteListener {
            if(it.isSuccessful){
                for (item in it.result!!){
                    val row = item.data
                    if(auth?.currentUser?.uid == row["id"]){
                        view.profile_firstname.text = row["firstname"].toString()
                        view.profile_lastname.text = row["lastname"].toString()
                        view.profile_email.text = row["email"].toString()
                    }
                }
            }
        }

        view.profile_password_change.setOnClickListener {
            auth?.currentUser?.email?.let { it1 -> auth?.sendPasswordResetEmail(it1)?.addOnCompleteListener {
                    Toast.makeText(view.context, "Password reset by email sended!", Toast.LENGTH_LONG).show()
                }
            }
        }

        return view
    }
}