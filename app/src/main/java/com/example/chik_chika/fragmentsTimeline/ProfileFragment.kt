package com.example.chik_chika.fragmentsTimeline

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.chik_chika.MainActivity
import com.example.chik_chika.R
import com.example.chik_chika.fragments.LoginFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var imageVewPicture : ImageView
    private lateinit var textViewMail : TextView
    private lateinit var signOut : Button
    private lateinit var textViewChangePassword : TextView
    private val data = FirebaseDatabase.getInstance().getReference("UserInfo")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        signOutListener()
        changePasswordListener()




        val userMail = FirebaseAuth.getInstance().currentUser?.email
        textViewMail.text = userMail

    }

    private fun init(){
        imageVewPicture = requireView().findViewById(R.id.imageViewPicture)
        textViewMail = requireView().findViewById(R.id.textViewMail)
        signOut = requireView().findViewById(R.id.signOut)
        textViewChangePassword = requireView().findViewById(R.id.textViewChangePassword)

    }

    private fun changePasswordListener() {
        textViewChangePassword.setOnClickListener(){

            val controller = view?.let { it1 -> Navigation.findNavController(it1) }
            val action = ProfileFragmentDirections.actionProfileToChangePasswordFragment()
            controller?.navigate(action)
        }

    }



    private fun signOutListener(){
        signOut.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
        }
    }













}