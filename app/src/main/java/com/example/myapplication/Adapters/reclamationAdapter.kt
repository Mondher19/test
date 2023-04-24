package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.Reclamation
import com.example.myapplication.R

class reclamationAdapter (private var mList: List<Reclamation>) : RecyclerView.Adapter<reclamationAdapter.ViewHolder>(){
    var data: ArrayList<Reclamation> = ArrayList(mList) // update the data property with mList
    var listener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reclamationrsadmin, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.usernamerec.text = ItemsViewModel.username
        holder.descriptionrec.text = ItemsViewModel.Description
        holder.imageView.setImageResource(R.drawable.ic_baseline_report_24)


        // set click listener to view holder
        holder.itemView.setOnClickListener {
            listener?.invoke(ItemsViewModel._id)
        }

    }

    // add this for Filter (Search)
    fun setFilteredList(mList: List<Reclamation>) {
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
        val usernamerec: TextView = itemView.findViewById(R.id.usernamerec)
        val descriptionrec: TextView = itemView.findViewById(R.id.descriptionrec)
        val imageView: ImageView = itemView.findViewById(R.id.imgreclamationadmin)
    }
}