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


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var imageVewPicture : ImageView
    private lateinit var textViewMail : TextView
    private lateinit var signOut : Button
    private lateinit var textViewChangePassword : TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        signOutListener()
        changePasswordListener()
        checkPermission()


        imageVewPicture.setOnClickListener( View.OnClickListener {
            loadImage()
        })

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

    val READIMAGE:Int=253
    fun checkPermission(){

        if(Build.VERSION.SDK_INT>=23){
            if(activity?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                } !=
                PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf( android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                return
            }
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            READIMAGE->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }else{
                    Toast.makeText(activity,"Cannot access your images",Toast.LENGTH_LONG).show()
                }
            }
            else-> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }

    val PICK_IMAGE_CODE=123
    fun loadImage(){

        var intent=Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==PICK_IMAGE_CODE  && data!=null && resultCode == RESULT_OK){

            val selectedImage=data.data
            val filePathColum= arrayOf(MediaStore.Images.Media.DATA)
            val cursor= activity?.contentResolver?.query(selectedImage!!,filePathColum,null,null,null)
            cursor!!.moveToFirst()
            val coulomIndex=cursor!!.getColumnIndex(filePathColum[0])
            val picturePath=cursor!!.getString(coulomIndex)
            cursor!!.close()
            imageVewPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }

    }











}