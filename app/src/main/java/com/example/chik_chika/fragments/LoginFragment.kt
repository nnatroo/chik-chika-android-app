package com.example.chik_chika.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.chik_chika.R
import com.example.chik_chika.TimelineActivity
import com.google.firebase.auth.FirebaseAuth

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
        val controller = Navigation.findNavController(view)


        init()
        loginListeners()

//        fragments
        buttonReset.setOnClickListener(){

            val action = LoginFragmentDirections.actionLoginFragmentToForgetPasswordFragment()
            controller.navigate(action)
        }

        buttonRegister.setOnClickListener(){
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            controller.navigate(action)
        }


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
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextMail.error = "Enter valid e-mail address. Try again."
                return@setOnClickListener
            }
            if(!(email.matches(emailPattern.toRegex())) &&
                !(email.matches(emailPattern2.toRegex()))){
                editTextMail.error = "Incorrect Email"
                return@setOnClickListener
            }
            if (!(password.matches(".*[A-Z].*".toRegex())) &&
                !(password.matches(".*[a-z].*".toRegex())) ){
                editTextPassword.error = "Must contain letters"
                return@setOnClickListener
            }
            if (!(password.matches(".*[0-9].*".toRegex()))){
                editTextPassword.error = "Must contain digits"
                return@setOnClickListener
            }
            if (!(password.matches(".*[$@#!?_].*".toRegex()))){
                editTextPassword.error = "Must contain special symbols '$@#!?_'"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task -> if(task.isSuccessful){

                    startActivity(Intent(activity, TimelineActivity::class.java))
                    getActivity()?.finish()

            }else {
                    Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
                }
            }




        }

    }





}