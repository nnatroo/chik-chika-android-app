package com.example.chik_chika.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.chik_chika.R
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordFragment: Fragment(R.layout.fragment_forget) {
    private lateinit var editEmail : EditText
    private lateinit var buttonSend : Button
    private lateinit var signInPageButton : TextView

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        editEmail = view.findViewById(R.id.editEmail)
        buttonSend = view.findViewById(R.id.buttonSend)
        signInPageButton = view.findViewById(R.id.signInPageButton)



        resetListener()



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
            if(!(email.matches(emailPattern.toRegex())) &&
                !(email.matches(emailPattern2.toRegex()))){
                editEmail.error = "Incorrect Email"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task -> if(task.isSuccessful){
                    Toast.makeText(getActivity(), "Check E-mail for new password", Toast.LENGTH_SHORT).show()

                    val controller = Navigation.findNavController(view)
                    val action = ForgetPasswordFragment.
                    controller.navigate(action)

                }else {
                    Toast.makeText(activity, "Wrong Email, Try again !", Toast.LENGTH_SHORT).show()
                }
                }
        }

    }
}
