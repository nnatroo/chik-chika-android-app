package com.example.chik_chika.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.chik_chika.R
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val nextBtn : Button = view.findViewById(R.id.buttonRegister)
        nextBtn.setOnClickListener() {
            val fragment = RegisterFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.nav_host_fragment, fragment)?.commit()
        }
        return view
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

                }
                }




        }

    }

    private fun gotoProfile(){

    }



}