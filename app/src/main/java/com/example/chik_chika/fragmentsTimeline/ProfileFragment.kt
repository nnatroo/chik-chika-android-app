package com.example.chik_chika.fragmentsTimeline

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.chik_chika.MainActivity
import com.example.chik_chika.R
import com.example.chik_chika.UserInfo
import com.example.chik_chika.fragments.LoginFragmentDirections
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.app


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var imageViewPicture : ImageView
    private lateinit var editTextName : EditText
    private lateinit var textViewMail : TextView
    private lateinit var signOut : Button
    private lateinit var textViewChangePassword : TextView
    private lateinit var buttonSave : Button
    private lateinit var editTextUrl : EditText
    private lateinit var editTextBio : EditText
    private lateinit var editTextTweet : EditText
    private lateinit var buttonTweet : Button



    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")
    private val dbTweets = FirebaseDatabase.getInstance().getReference("Tweets")


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        signOutListener()
        changePasswordListener()
        saveButtonListeners()
        tweetListener()


        val userMail = FirebaseAuth.getInstance().currentUser?.email
        textViewMail.text = userMail

        db.child(auth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo = snapshot.getValue(UserInfo::class.java) ?: return


                if (userInfo.name != ""){
                    editTextName.hint = userInfo.name}

                if (userInfo.bio != ""){
                    editTextBio.hint = userInfo.bio
                }

                if (userInfo.url != "") {
                    editTextUrl.hint = userInfo.url
                }

                Glide.with(this@ProfileFragment).load(userInfo.url).placeholder(R.drawable.profile_picture).into(imageViewPicture)

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    private fun tweetListener(){
        buttonTweet.setOnClickListener {
            val text = editTextTweet.text.toString()
            db.child(auth.currentUser?.uid!!).get().addOnSuccessListener {
                if (it.exists()) {
                    val tweetMessage = editTextTweet.text.toString()
                    if (tweetMessage.isNotEmpty()){
                        val id = dbTweets.push().key
                        dbTweets.child(id.toString()).child("userName").setValue(editTextName.hint.toString())
                        dbTweets.child(id.toString()).child("text").setValue(text)
                        dbTweets.child(id.toString()).child("pictureUrl").setValue(editTextUrl.hint.toString())
                        Toast.makeText(activity, "The post is published", Toast.LENGTH_LONG).show()

                    }else Toast.makeText(activity, "faill", Toast.LENGTH_LONG).show()

                }
            }

        }

    }


    private fun init(){
        imageViewPicture = requireView().findViewById(R.id.imageViewPicture)
        textViewMail = requireView().findViewById(R.id.textViewMail)
        signOut = requireView().findViewById(R.id.signOut)
        textViewChangePassword = requireView().findViewById(R.id.textViewChangePassword)
        buttonSave = requireView().findViewById(R.id.buttonSave)
        editTextName = requireView().findViewById(R.id.editTextName)
        editTextUrl = requireView().findViewById(R.id.editTextUrl)
        editTextBio = requireView().findViewById(R.id.editTextBio)
        editTextTweet = requireView().findViewById(R.id.editTextTweet)
        buttonTweet = requireView().findViewById(R.id.buttonTweet)

    }

    private fun changePasswordListener() {
        textViewChangePassword.setOnClickListener(){

            val controller = view?.let { it1 -> Navigation.findNavController(it1) }
            val action = ProfileFragmentDirections.actionProfileToChangePasswordFragment()
            controller?.navigate(action)
        }

    }
    private fun saveButtonListeners() {
        buttonSave.setOnClickListener {

            val name = editTextName.text.toString()

            if (name.isEmpty()) {
                editTextName.error = "Enter Name"
            }
            val url = editTextUrl.text.toString()
            val bio = editTextBio.text.toString()
            val userInfo = UserInfo(name, url, bio)



            db.child(auth.currentUser?.uid!!).setValue(userInfo)
            Toast.makeText(activity, "Changes Saved Successfully", Toast.LENGTH_SHORT).show()


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




