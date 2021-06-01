@file:Suppress("DEPRECATION", "LABEL_NAME_CLASH")

package com.cals.getloc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cals.getloc.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthActionCodeException

class ChangePasswordFragment : Fragment() {
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val layoutPassword: LinearLayout = view.findViewById(R.id.layoutPassword)
        val layoutNewPassword: LinearLayout = view.findViewById(R.id.layoutNewPassword)
        val etPassword: EditText = view.findViewById(R.id.etPassword)
        val etNewPassword: EditText = view.findViewById(R.id.etNewPassword)
        val etNewPasswordConfirm: EditText = view.findViewById(R.id.etNewPasswordConfirm)
        val btnAuth: Button = view.findViewById(R.id.btnAuth)
        val btnUpdate: Button = view.findViewById(R.id.btnUpdate)

        layoutPassword.visibility = View.VISIBLE
        layoutNewPassword.visibility = View.GONE

        btnAuth.setOnClickListener {
            val password = etPassword.text.toString().trim()
            if (password.isEmpty()){
                etPassword.error = "Password Harus Diisi"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            user?.let{
                val userCredentials = EmailAuthProvider.getCredential(it.email!!, password)
                it.reauthenticate(userCredentials).addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> {
                            layoutPassword.visibility = View.GONE
                            layoutNewPassword.visibility = View.VISIBLE
                        }
                        task.exception is FirebaseAuthActionCodeException -> {
                            etPassword.error = "Password Salah"
                            etPassword.requestFocus()
                        }
                        else -> {
                            Toast.makeText(activity, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            btnUpdate.setOnClickListener {
                val newPassword = etNewPassword.text.toString().trim()
                val newPasswordConfirm = etNewPasswordConfirm.text.toString().trim()

                if (newPassword.isEmpty() || newPassword.length<6){
                    etNewPassword.error = "Password harus diisi! dan lebih dari 6 Karakter"
                    etNewPassword.requestFocus()
                    return@setOnClickListener
                }

                if (newPassword != newPasswordConfirm ){
                    etNewPasswordConfirm.error = "Password Tidak Sama"
                    etNewPasswordConfirm.requestFocus()
                    return@setOnClickListener
                }


                user.let {
                    user?.updatePassword(newPassword)?.addOnCompleteListener {
                        if (it.isSuccessful){
                            val profileFragment = ProfileFragment()
                            fragmentManager?.beginTransaction()?.apply {
                                replace(R.id.frameLayout, profileFragment, ProfileFragment::class.java.simpleName)
                                    .addToBackStack(null)
                                    .commit()
                            }
                            Toast.makeText(activity, "Password telah berhasil diubah", Toast.LENGTH_SHORT).show()
                        } else{
                            Toast.makeText(activity, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }



    }

}