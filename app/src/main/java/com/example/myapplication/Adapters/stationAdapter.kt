package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.station
import com.example.myapplication.R

class stationAdapter(private var mList: List<station>) : RecyclerView.Adapter<stationAdapter.ViewHolder>() {

    var data: ArrayList<station> = ArrayList(mList) // update the data property with mList
    var listener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.stationrs, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.name.text = ItemsViewModel.name
        holder.type.text = ItemsViewModel.type

        var type = ItemsViewModel.type

        if ( type == "Bus")
        {
            holder.imageView.setImageResource(R.drawable.stationbus)
        }
        else if (type == "Train"){
            holder.imageView.setImageResource(R.drawable.stationtrain)
        }
        else{
            holder.imageView.setImageResource(R.drawable.stationmetro)

        }

        // set click listener to view holder
        holder.itemView.setOnClickListener {
            listener?.invoke(ItemsViewModel._id)
        }

    }

    // add this for Filter (Search)
    fun setFilteredList(mList: List<station>) {
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
        val name: TextView = itemView.findViewById(R.id.namestation)
        val type: TextView = itemView.findViewById(R.id.typestation)
        val imageView: ImageView = itemView.findViewById(R.id.imageviewuseradmin)
    }
}