package com.example.chik_chika.fragments

import android.os.Bundle
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
            }

            if(password.isEmpty()){
                editTextPassword.error = "Password is empty!"
            }




        }
    }





}