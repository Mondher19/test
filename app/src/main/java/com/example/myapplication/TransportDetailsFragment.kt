package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TransportDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransportDetailsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_transport_details, container, false)

        val backtbtn: ImageView = view.findViewById(R.id.backtn3)

        val stationtype = arguments?.getString("type")
        val sharedPreferences = requireActivity().getSharedPreferences("MyPreferences", Activity.MODE_PRIVATE)
        val stationId = sharedPreferences.getString("stationId", null)

        backtbtn.setOnClickListener {
            val fragment = TransportFragment()
            val bundle = Bundle()
            bundle.putString("stationId", stationId)
            bundle.putString("stationtype", stationtype)
            fragment.arguments = bundle
            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.switchfragment, fragment)
                .addToBackStack(null)
                .commit()
        }


        val transportname = arguments?.getString("name")
        val transportpricee = arguments?.getString("price")
        val schedule = arguments?.getString("schedule")
        val transportdestination = arguments?.getString("destination")
        val type = arguments?.getString("type")

        val transportnametext: TextView = view.findViewById(R.id.nameuser)
        val transportpricetext: TextView = view.findViewById(R.id.adresseuser)
        val transportscheduletext: TextView = view.findViewById(R.id.shedule)
        val transportdestinationtext: TextView = view.findViewById(R.id.emailuser)


        val img: ImageView = view.findViewById(R.id.trasnportdetailimg)


        if ( type == "Bus")
        {
            img.setImageResource(R.drawable.busv2)
        }
        else if (type == "Train"){
            img.setImageResource(R.drawable.trainimg)
        }
        else{
            img.setImageResource(R.drawable.metroimg)

        }

        transportnametext.text = "Name : $transportname "
        transportpricetext.text = "Price : $transportpricee "
        transportscheduletext.text = "Schedule : $schedule "
        transportdestinationtext.text = "Destination : $transportdestination "

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TransportDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            schedule: String,
            destinantion: String,
            type: String
        ) =
            TransportDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}