package com.example.myapplication.Adapters

import android.os.Bundle
import com.example.myapplication.Model.transport
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.TransportDetailsFragment


class transportadminAdapter(private var mList: List<transport>) : RecyclerView.Adapter<transportadminAdapter.ViewHolder>() {

    var data: ArrayList<transport> = ArrayList(mList) // update the data property with mList
    var listener: ((String) -> Unit)? = null


    fun setOnClickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transportrsadmin, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.name.text = ItemsViewModel.name
        holder.price.text = ItemsViewModel.price
        //holder.destination.text = ItemsViewModel.destination



        //var destinantion = ItemsViewModel.destination
        var schedule = ItemsViewModel.shedule
        var type = ItemsViewModel.type

        if ( type == "Bus")
        {
            holder.imageView.setImageResource(R.drawable.busimage)
        }
        else if (type == "Train"){
            holder.imageView.setImageResource(R.drawable.trainimg)
        }
        else if (type == "Metro"){
            holder.imageView.setImageResource(R.drawable.metroimg)

        }

        // set click listener to view holder
        holder.itemView.setOnClickListener {

        }

        holder.itemView.setOnClickListener{
            listener?.invoke(ItemsViewModel._id)
            // pass data to the new fragment using arguments
            val bundle = Bundle()
            bundle.putString("name", holder.name.text.toString())
            bundle.putString("price", holder.price.text.toString())
            bundle.putString("schedule", schedule)
            //  bundle.putString("destination", destinantion)
            bundle.putString("type", type)
            val fragment = TransportDetailsFragment.newInstance(holder.name.text.toString(), holder.price.text.toString(), schedule, "hello", type)

            fragment.arguments = bundle

            // navigate to the new fragment
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.switchfragment, fragment)
                .addToBackStack(null)
                .commit()
        }

    }

    // add this for Filter (Search)
    fun setFilteredList(mList: List<transport>) {
        this.mList = mList
        data = ArrayList(mList)
        notifyDataSetChanged()
    }

    // add this for Clearing the Data (Optional)
    fun clearData() {
        mList = emptyList()
        data = ArrayList()
        notifyDataSetChanged()
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.nametransportadmin)
        val price: TextView = itemView.findViewById(R.id.destinationtransportadmin)
        // val destination: TextView = itemView.findViewById(R.id.destinationtransport)
        val imageView: ImageView = itemView.findViewById(R.id.imageviewuseradmin)


    }




}

