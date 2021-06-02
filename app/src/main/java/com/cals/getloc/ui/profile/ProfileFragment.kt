@file:Suppress("UNREACHABLE_CODE", "DEPRECATION")

package com.cals.getloc.ui.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cals.getloc.R
import com.cals.getloc.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class ProfileFragment: Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var imgUri : Uri

    companion object{
        const val REQUEST_CAMERA = 100
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_Logout:Button = view.findViewById(R.id.btnLogout)
        val btn_Update:Button = view.findViewById(R.id.btnUpdate)
        val iv_Profile: ImageView = view.findViewById(R.id.ivProfile)
        val etName: EditText = view.findViewById(R.id.etUsername)
        val etEmail: EditText = view.findViewById(R.id.etEmail)
        val tvChangePassword: TextView = view.findViewById(R.id.btnChangePassword)
        val icVerified: ImageView = view.findViewById(R.id.ic_verified)
        val icUnverified: ImageView = view.findViewById(R.id.ic_unverified)
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        if ( user != null){
            if (user.photoUrl != null){
                Picasso.get().load(user.photoUrl).into(iv_Profile)
            } else
            {
                Picasso.get().load("https://picsum.photos/316/200").into(iv_Profile)
            }

            etName.setText(user.displayName)
            etEmail.setText(user.email)
                if (user.isEmailVerified){
                    icVerified.visibility = View.VISIBLE
                } else{
                    icUnverified.visibility =View.VISIBLE
                }


        }

        btn_Logout.setOnClickListener {
            auth.signOut()
            Intent(requireContext(), LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        iv_Profile.setOnClickListener {
            intentCamera()
        }

        btn_Update.setOnClickListener {
            val image = when{
                ::imgUri.isInitialized -> imgUri
                user?.photoUrl == null -> Uri.parse("https://picsum.photos/316/200")
                else -> user.photoUrl
            }

            val name = etName.text.toString().trim()
            if (name.isEmpty()){
                etName.error = "Nama Harus diisi"
                etName.requestFocus()
                return@setOnClickListener
            }

            UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(image)
                .build().also {
                    user?.updateProfile(it)?.addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(activity, "Profile Berhasil diUpdate", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity, "$(it.exception?.message)", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }

        icUnverified.setOnClickListener {
            user?.sendEmailVerification()?.addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(activity, "Email Verifikasi Telah dikirim", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(activity, "$(it.exception?.message)", Toast.LENGTH_SHORT).show()
                }
            }
        }

        etEmail.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_updateEmailFragment)
        }

        tvChangePassword.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_changePasswordFragment)
        }

    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun intentCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            activity?.packageManager?.let {
                intent.resolveActivity(it).also {
                    startActivityForResult(intent, REQUEST_CAMERA)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK){
            val imgBitmap = data?.extras?.get("data") as Bitmap
            uploadImage(imgBitmap)
        }
    }

    private fun uploadImage(imgBitmap: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val ref = FirebaseStorage.getInstance().reference.child("img/${FirebaseAuth.getInstance().currentUser?.uid }")

        val iv_Profile: ImageView = requireView().findViewById(R.id.ivProfile)

        imgBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val image = byteArrayOutputStream.toByteArray()

        ref.putBytes(image)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    ref.downloadUrl.addOnCompleteListener{ task->
                        task.result?.let { intent ->
                            imgUri = intent

                            iv_Profile.setImageBitmap(imgBitmap)
                        }
                    }
                }
            }

    }
}