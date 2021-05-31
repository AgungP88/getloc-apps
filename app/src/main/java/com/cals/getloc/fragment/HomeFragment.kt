@file:Suppress("DEPRECATION")

package com.cals.getloc.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.cals.getloc.R
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class HomeFragment: Fragment() {

    lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    private var PERMISSION_ID = 100
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnSearch: ImageView = view.findViewById(R.id.btnsearch)
        val nameUser: TextView = view.findViewById(R.id.username)
        val btnPosition: LinearLayout = view.findViewById(R.id.btn_location)
        val planFragment = PlanFragment()
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        nameUser.text = "Hello, "+ user?.displayName

        btnSearch.setOnClickListener {
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameLayout, planFragment, PlanFragment::class.java.simpleName)
                    .addToBackStack(null)
                    .commit()

            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        btnPosition.setOnClickListener {
            getLastLocation()
        }
    }
    private fun checkPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ){
            return true
        }

        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION),  PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{
        var locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.d("Debug:", "Permission Done ")
            }
        }
    }

    private fun getLastLocation(){
        val locationtxt: TextView = requireView().findViewById(R.id.location)

        if (checkPermission()){
            if (isLocationEnabled()){
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task->
                    var location = task.result
                    if (location == null){
                        getNewLocation()

                    }else{
                        locationtxt.text = getCityName(location.latitude, location.longitude)
                        savedStateRegistry
                    }

                }

            }else{
                Toast.makeText(requireContext(),"Please Enable your Location Service", Toast.LENGTH_SHORT).show()
            }

        } else{
            requestPermission()
        }
    }

    private fun getNewLocation(){
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,locationCallback,Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            val locationtxt: TextView = requireView().findViewById(R.id.location)
            var lastLocation = p0.lastLocation
            locationtxt.text = getCityName(lastLocation.latitude, lastLocation.longitude)
        }
    }

    private fun getCityName(lat:Double, long:Double): String{
        var CityName = ""
        var geoCoder = Geocoder(requireContext(), Locale.getDefault())
        var address = geoCoder.getFromLocation(lat,long, 1)

        CityName = address.get(0).locality
        return CityName

    }

    private fun getCountryName(lat:Double, long:Double): String{
        var CityName = ""
        var geoCoder = Geocoder(requireContext(), Locale.getDefault())
        var address = geoCoder.getFromLocation(lat,long, 1)

        CityName = address.get(0).countryName
        return CityName

    }
}