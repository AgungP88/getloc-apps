@file:Suppress("UNREACHABLE_CODE")

package com.cals.getloc.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.cals.getloc.R
import com.cals.getloc.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment: Fragment() {

    private lateinit var auth: FirebaseAuth

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

        btn_Logout.setOnClickListener {
            auth.signOut()
            Intent(requireContext(), LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }




    }
}