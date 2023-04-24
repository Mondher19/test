package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Adapters.UserAdapter
import com.example.myapplication.Adapters.reclamationAdapter
import com.example.myapplication.Model.Reclamation
import com.example.myapplication.Model.User
import com.example.myapplication.services.ApiHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReclamationAdmin.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReclamationAdmin : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var searchView: SearchView
    private lateinit var adapter: reclamationAdapter
    private lateinit var mList: List<Reclamation>

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
        val view = inflater.inflate(R.layout.fragment_reclamation_admin, container, false)
        val searchView = view.findViewById<SearchView>(R.id.searchView)
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerviewreclamation)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        // initialize the adapter with an empty list
        adapter = reclamationAdapter(emptyList())

        recyclerview.adapter = adapter

        // fetch data from the API
        ApiHelper().fetchReclamation { reclamations ->
            requireActivity().runOnUiThread {
                // initialize mList with the fetched data
                mList = reclamations
                // update the adapter with the fetched data
                adapter = reclamationAdapter(mList)
                recyclerview.adapter = adapter

                adapter.setOnClickListener { userId ->
                    Toast.makeText(requireContext(), "Selected Reclamation id: $userId", Toast.LENGTH_SHORT).show()

                    // ...
                }
            }
        }

        // return the view after initializing the RecyclerView, layout manager, and adapter
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

    // ...


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
        val filteredList: List<Reclamation> = if (text.isNullOrEmpty()) {
            mList // no filter, return the original list
        } else {
            mList.filter { it.username.contains(text, ignoreCase = true) } // filter the list based on text
        }
        adapter.setFilteredList(filteredList) // assign the filtered list to the adapter
    }

}