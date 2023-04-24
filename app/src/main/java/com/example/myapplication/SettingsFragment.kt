package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val fragmentManager = requireFragmentManager()

        val editProfileCard: CardView = view.findViewById(R.id.editprofilecard)
        val notificationCard: CardView = view.findViewById(R.id.Notification)
        val privatePolicyCard: CardView = view.findViewById(R.id.PrivatePolicy)
        val languagesCard: CardView = view.findViewById(R.id.Languages)
        val logoutCard: CardView = view.findViewById(R.id.Logout)

        editProfileCard.setOnClickListener {
            val editProfileFragment = EditProfileFragment()
            val bundle = Bundle()

            editProfileFragment.arguments = bundle
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.switchfragment, editProfileFragment)
            transaction.commit()
        }

        notificationCard.setOnClickListener {
            val notificationFragment = NotificationFragment()
            val bundle = Bundle()

            notificationFragment.arguments = bundle
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.switchfragment, notificationFragment)
            transaction.commit()
        }

        privatePolicyCard.setOnClickListener {
            val privatePolicyFragment = PrivatePolicyFragment()
            val bundle = Bundle()

            privatePolicyFragment.arguments = bundle
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.switchfragment, privatePolicyFragment)
            transaction.commit()
        }

        languagesCard.setOnClickListener {
            val languagesFragment = LanguagesFragment()
            val bundle = Bundle()

            languagesFragment.arguments = bundle
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.switchfragment, languagesFragment)
            transaction.commit()
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
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}