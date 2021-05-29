@file:Suppress("DEPRECATION")

package com.cals.getloc.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.cals.getloc.R

class HomeFragment: Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSearch: ImageView = view.findViewById(R.id.btnsearch)
        val planFragment = PlanFragment()

        btnSearch.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, planFragment, PlanFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }
}