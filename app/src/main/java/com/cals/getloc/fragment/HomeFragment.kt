@file:Suppress("DEPRECATION")

package com.cals.getloc.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.cals.getloc.R
import com.cals.getloc.adapter.HomeAdapter
import com.cals.getloc.api.ApiEndpoint
import com.cals.getloc.model.HomeModel
import org.json.JSONException
import org.json.JSONObject
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
class HomeFragment: Fragment() {


    private var homeAdapter: HomeAdapter? = null
    private var homeModel: MutableList<HomeModel> = ArrayList()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getListPlace()
        showRecyclerPlace()
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

    private fun showRecyclerPlace() {
        homeAdapter = HomeAdapter(this, homeModel)

        val rvRekomendasi: RecyclerView? = view?.findViewById(R.id.rv_rekomendasi)

        rvRekomendasi?.setLayoutManager(LinearLayoutManager(requireContext()))
        rvRekomendasi?.setHasFixedSize(true)
        rvRekomendasi?.setAdapter(homeAdapter)
    }

    private fun getListPlace() {
        AndroidNetworking.get(ApiEndpoint.BASEURL + ApiEndpoint.getAllList)
            .addHeaders("", "AIzaSyBiwclUtu8jpUy_KDD3hwX0n8k8AOUbfCU")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        homeModel = ArrayList()
                        val jsonArray = response.getJSONArray("tempat_pilihan")

                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val dataApi = HomeModel()
                            val jsonObjectData = jsonObject.getJSONObject("data")

                            dataApi.city = jsonObjectData.getString("city")
                            dataApi.namePlace = jsonObjectData.getString("name")
                            homeModel.add(dataApi)
                        }
                        showRecyclerPlace()
                        homeAdapter?.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(requireContext(), "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(requireContext(), "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            })
    }

}