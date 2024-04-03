package com.example.project6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AmiiboAdapter(private val amiiboList: List<amiibo>) : RecyclerView.Adapter<AmiiboAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amiiboImage: ImageView
        val amiiboGameSeries: TextView
        var amiiboName: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            amiiboImage = view.findViewById(R.id.amiibo_image)
            amiiboGameSeries = view.findViewById(R.id.amiibo_game_series)
            amiiboName = view.findViewById(R.id.amiibo_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = amiiboList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("amiibo", amiiboList[position].image_link)
        Glide.with(holder.itemView)
            .load(amiiboList[position].image_link)
            .fitCenter()
            .into(holder.amiiboImage)
        holder.amiiboName.text = amiiboList[position].name
        holder.amiiboGameSeries.text = amiiboList[position].game_series

    }

}