package com.example.chik_chika.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.chik_chika.R

class ForgetPasswordFragment: Fragment(R.layout.fragment_forget) {
    private lateinit var editEmail : EditText
    private lateinit var buttonSend : Button
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editEmail = view.findViewById(R.id.editEmail)



    }

    private fun resetListener(){
        buttonSend.setOnClickListener(){
            val email = editEmail.text.toString()

            if (email.isEmpty()){
                editEmail.error = "Enter E-mail !"
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editEmail.error = "Enter valid e-mail address. Try again."
                return@setOnClickListener
            }
            if(!(email.matches(emailPattern.toRegex())) && !(email.matches(emailPattern2.toRegex()))){
                editEmail.error = "Incorrect Email"
                return@setOnClickListener
            }
        }

    }
}
