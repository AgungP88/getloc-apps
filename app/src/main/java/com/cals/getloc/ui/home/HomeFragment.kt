@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS", "DEPRECATION")

package com.cals.getloc.ui.home

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class HomeFragment: Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: HomeAdapter
    private lateinit var adapters: BundleAdapter
    private lateinit var  fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
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
        val btnRekomendasi : TextView = view.findViewById(R.id.btn_LihatSemuaRekomendasi)
        val btnPilihan : TextView = view.findViewById(R.id.btn_LihatSemuaPilihan)
        val nameUser: TextView = view.findViewById(R.id.username)
        val btnPosition: LinearLayout = view.findViewById(R.id.btn_location)
        auth = FirebaseAuth.getInstance()


        val user = auth.currentUser
        nameUser.text = "Hello, "+ user?.displayName

        adapter = HomeAdapter()
        adapters = BundleAdapter()

        btnSearch.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_navigation_plan)
        }

        btnRekomendasi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_rekomendasiActivity)
        }

        btnPilihan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_pilihanActivity)
        }

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val rv_Pilih = view.findViewById<RecyclerView>(R.id.rv_pilih)
        rv_Pilih.setHasFixedSize(true)
        rv_Pilih.layoutManager = LinearLayoutManager(activity)
        rv_Pilih.adapter = adapter

        val rv_Rekomendasi = view.findViewById<RecyclerView>(R.id.rv_rekomendasi)
        rv_Rekomendasi.setHasFixedSize(true)
        rv_Rekomendasi.layoutManager = LinearLayoutManager(activity)
        rv_Rekomendasi.adapter = adapters

        homeViewModel.setWisataRekomendasi()
        homeViewModel.setWisataPilihan()
        homeViewModel.getWisata().observe(viewLifecycleOwner, {
            if (it!=null){
                adapter.setList(it)
                adapters.setLists(it)
            }
        })

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity)

        btnPosition.setOnClickListener {
            getLastLocation()
        }
    }

    private fun checkPermission():Boolean{
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ){
            return true
        }

        return false
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{
        val locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
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
                    val location = task.result
                    if (location == null){
                        getNewLocation()

                    }else{
                        locationtxt.text = getCityName(location.latitude, location.longitude)
                        savedStateRegistry
                    }

                }

            }else{
                Toast.makeText(
                    requireContext(),
                    "Please Enable your Location Service",
                    Toast.LENGTH_SHORT
                ).show()
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
            locationRequest, locationCallback, Looper.myLooper()
        )
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            val locationtxt: TextView = requireView().findViewById(R.id.location)
            val lastLocation = p0.lastLocation
            locationtxt.text = getCityName(lastLocation.latitude, lastLocation.longitude)
        }
    }

    private fun getCityName(lat: Double, long: Double): String{
        val CityName: String
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val address = geoCoder.getFromLocation(lat, long, 1)

        CityName = address[0].locality
        return CityName

    }
}