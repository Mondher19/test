package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import com.example.myapplication.Adapters.stationAdapter
import com.example.myapplication.Model.station

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseTransportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseTransportFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var searchView: SearchView
    private lateinit var adapter: stationAdapter
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
        val view = inflater.inflate(R.layout.fragment_choose_transport, container, false)

        var btnbus: ImageView = view.findViewById(R.id.Busbtn)
        var metrobtn: ImageView = view.findViewById(R.id.Metrobtn)
        var trainbtn: ImageView = view.findViewById(R.id.Trainbtn)


        btnbus.setOnClickListener {
            val stationsFragment = StationsFragment()
            val bundle = Bundle()
            bundle.putString("stationtype", "Bus")
            stationsFragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.switchfragment, stationsFragment)
            transaction?.commit()
        }

        metrobtn.setOnClickListener {
            val stationsFragment = StationsFragment()
            val bundle = Bundle()
            bundle.putString("stationtype", "Metro")
            stationsFragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.switchfragment, stationsFragment)
            transaction?.commit()
        }

        trainbtn.setOnClickListener {
            val stationsFragment = StationsFragment()
            val bundle = Bundle()
            bundle.putString("stationtype", "Train")
            stationsFragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.switchfragment, stationsFragment)
            transaction?.commit()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChooseTransportFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChooseTransportFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}