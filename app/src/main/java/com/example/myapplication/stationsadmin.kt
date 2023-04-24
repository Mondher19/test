package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapters.stationAdapter
import com.example.myapplication.Adapters.stationadminAdapter
import com.example.myapplication.Model.station
import com.example.myapplication.services.ApiHelper


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [stationsadmin.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("UNREACHABLE_CODE")
class stationsadmin : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var searchView: SearchView
    private lateinit var adapter: stationadminAdapter
    private lateinit var mList: List<station>

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
        val view = inflater.inflate(R.layout.fragment_stationsadmin, container, false)

        val stationtype = arguments?.getString("stationtype")

        val backtbtn: ImageView? = view?.findViewById(R.id.backtn1admin)




        if (backtbtn != null) {
            backtbtn.setOnClickListener {
                val fragment = ChooseTransportAdmin()
                val bundle = Bundle()
                fragment.arguments = bundle
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.beginTransaction()
                    .replace(R.id.switchfragment, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }


        // getting the recyclerview by its id
        val recyclerview = view?.findViewById<RecyclerView>(R.id.recyclerviewstation)
        searchView = view.findViewById(R.id.searchView)
        // this creates a vertical layout Manager
        if (recyclerview != null) {
            recyclerview.layoutManager = LinearLayoutManager(requireContext())
        }

        // initialize the adapter with an empty list
        adapter = stationadminAdapter(emptyList())

        // set the adapter with the recyclerview
        if (recyclerview != null) {
            recyclerview.adapter = adapter
        }

        // fetch data from the API
        if (stationtype != null) {
            ApiHelper().fetchDataFromApi(stationtype) { stations ->
                requireActivity().runOnUiThread {
                    // initialize mList with the fetched data
                    mList = stations
                    // update the adapter with the fetched data
                    adapter = stationadminAdapter(stations)
                    if (recyclerview != null) {
                        recyclerview.adapter = adapter
                    }

                    adapter.setOnClickListener { stationid ->
                        Toast.makeText(requireContext(), "Selected station id: $stationid", Toast.LENGTH_SHORT).show()

                        val transportFragment = TransportFragment()

                        // pass data to the new fragment using arguments
                        val bundle = Bundle()
                        bundle.putString("stationId", stationid)
                        bundle.putString("stationtype", stationtype)
                        transportFragment.arguments = bundle

                        // navigate to the new fragment
                        val fragmentManager = requireActivity().supportFragmentManager
                        fragmentManager.beginTransaction()
                            .replace(R.id.switchfragment, transportFragment)
                            .addToBackStack(null)
                            .commit()

                        val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Activity.MODE_PRIVATE)
                        sharedPreferences.edit().putString("stationId", stationid).apply()
                        sharedPreferences.edit().putString("stationtype", stationtype).apply()


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
         * @return A new instance of fragment StationsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun filter(text: String?) {
        val filteredList: List<station> = if (text.isNullOrEmpty()) {
            mList // no filter, return the original list
        } else {
            mList.filter { it.name.contains(text, ignoreCase = true) } // filter the list based on text
        }
        adapter.setFilteredList(filteredList) // assign the filtered list to the adapter
    }

}