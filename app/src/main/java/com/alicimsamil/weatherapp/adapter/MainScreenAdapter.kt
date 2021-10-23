package com.alicimsamil.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.model.LocationsModel

class MainScreenAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var locations = listOf<LocationsModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.location_recycler_shape,parent,false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.locationTitle).text=locations.get(position).title
    }

    override fun getItemCount(): Int {
        return locations.size
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}