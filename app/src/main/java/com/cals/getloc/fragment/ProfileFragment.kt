@file:Suppress("UNREACHABLE_CODE", "DEPRECATION")

package com.cals.getloc.fragment

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
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.cals.getloc.R
import com.cals.getloc.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
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

        auth = FirebaseAuth.getInstance()

        val btn_Logout:Button = view.findViewById(R.id.btnLogout)
        val iv_Profile: ImageView = view.findViewById(R.id.ivProfile)



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