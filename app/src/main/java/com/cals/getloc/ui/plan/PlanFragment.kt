package com.cals.getloc.ui.plan

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R

class PlanFragment : Fragment() {

    private lateinit var planViewModel: PlanViewModel
    private lateinit var adapter: PlanAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSearch= view.findViewById<ImageView>(R.id.btn_search)
        val etQuery = view.findViewById<TextView>(R.id.et_query)
        val rv_pilih = view.findViewById<RecyclerView>(R.id.rv_pilihanPlan)


        btnSearch.setOnClickListener {
            searchWisata()
        }

        etQuery.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchWisata()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }


        planViewModel =
            ViewModelProvider(this).get(PlanViewModel::class.java)

        adapter = PlanAdapter()
        adapter.notifyDataSetChanged()
        rv_pilih.setHasFixedSize(true)
        rv_pilih.layoutManager = LinearLayoutManager(activity)
        rv_pilih.adapter = adapter
        planViewModel.getSearchWisata().observe(viewLifecycleOwner, {
            if (it!=null){
                adapter.setList(it)
                progressbar(false)

            }
        })
    }

    private fun searchWisata(){
        val etQuerys = requireView().findViewById<TextView>(R.id.et_query)
        val query = etQuerys.text.toString()
        if (query.isEmpty()) return
        progressbar(true)
        planViewModel.setSearchWisata(query)
    }

    private fun progressbar(state: Boolean){
        val progressBar: ProgressBar = requireView().findViewById(R.id.progressBar)
        if (state){
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}