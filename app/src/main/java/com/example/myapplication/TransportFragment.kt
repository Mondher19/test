package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapters.transportAdapter
import com.example.myapplication.Model.transport
import com.example.myapplication.services.ApiHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var searchView: SearchView
lateinit var adapter: transportAdapter
lateinit var mList: List<transport>


/**
 * A simple [Fragment] subclass.
 * Use the [TransportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transport, container, false)

        var backtbtn: ImageView = view.findViewById(R.id.backtn2)
        val stationId = arguments?.getString("stationId")
        val stationtype = arguments?.getString("stationtype")


        backtbtn.setOnClickListener {
            val fragment = StationsFragment()
            val bundle = Bundle()
            bundle.putString("stationtype", stationtype)
            fragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.switchfragment, fragment)
                .addToBackStack(null)
                .commit()
        }


        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewtransport)
        searchView = view.findViewById(R.id.searchView)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // initialize the adapter with an empty list
        adapter = transportAdapter(emptyList())

        // set the adapter with the recyclerview
        recyclerview.adapter = adapter

        // fetch data from the API
        if (stationId != null) {
            ApiHelper().fetchTransportsFromApi(stationId) { transports ->
                requireActivity().runOnUiThread {
                    // initialize mList with the fetched data
                    mList = transports

                    // update the adapter with the fetched data
                    adapter = transportAdapter(transports)
                    recyclerview.adapter = adapter

                    adapter.setOnClickListener { station ->

                    }
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText)
                return true
            }
        })

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TransportFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun filter(text: String?) {
        val filteredList: List<transport> = if (text.isNullOrEmpty()) {
            mList // no filter, return the original list
        } else {
            mList.filter { it.name.contains(text, ignoreCase = true) } // filter the list based on text
        }
        adapter.setFilteredList(filteredList) // assign the filtered list to the adapter
    }
}