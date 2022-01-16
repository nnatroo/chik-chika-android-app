package com.example.chik_chika.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.chik_chika.R

class LoginFragment : Fragment(R.layout.fragment_login){

    private lateinit var editTextMail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var buttonLogin : Button
    private lateinit var buttonReset : Button
    private lateinit var buttonRegister : Button
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        loginListeners()


    }

    private fun init(){
        editTextMail = requireView().findViewById(R.id.editTextMail)
        editTextPassword = requireView().findViewById(R.id.editTextPassword)
        buttonLogin = requireView().findViewById(R.id.buttonLogin)
        buttonReset = requireView().findViewById(R.id.buttonReset)
        buttonRegister = requireView().findViewById(R.id.buttonRegister)


    }

    private fun loginListeners(){
        buttonLogin.setOnClickListener{

            val email = editTextMail.text.toString()
            val password = editTextPassword.text.toString()

            if(email.isEmpty()){
                editTextMail.error = "Mail is empty!"
                return@setOnClickListener
            }
            if(password.isEmpty()){
                editTextPassword.error = "Password is empty!"
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextMail.error = "Enter valid e-mail address. Try again."
                return@setOnClickListener
            }
            if (!(email.matches(emailPattern.toRegex())) && !(email.matches(emailPattern2.toRegex()))){
                editTextMail.error = "Incorrect Email"
                return@setOnClickListener
            }




        }
    }





}