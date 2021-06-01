package com.cals.getloc.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cals.getloc.R
import com.cals.getloc.fragment.CartFragment
import com.cals.getloc.fragment.HomeFragment
import com.cals.getloc.fragment.PlanFragment
import com.cals.getloc.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val homeFragment = HomeFragment()
    private val planFragment = PlanFragment()
    private val cartFragment = CartFragment()
    private val profileFragment = ProfileFragment()


    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        setCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    Log.i(TAG, "Home Selected")
                }
                R.id.nav_plan -> {
                    setCurrentFragment(planFragment)
                    Log.i(TAG, "Plan Selected")
                }
                R.id.nav_cart -> {
                    setCurrentFragment(cartFragment)
                    Log.i(TAG, "Cart Selected")
                }
                R.id.nav_profile -> {
                    setCurrentFragment(profileFragment)
                    Log.i(TAG, "Profile Selected")
                }
            }
            true
        }
    }




    private fun setCurrentFragment(fragment: Fragment) {
        val transactions = supportFragmentManager.beginTransaction()
        transactions.replace(R.id.frameLayout, fragment)
        transactions.commit()
    }
}