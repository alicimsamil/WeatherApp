package com.alicimsamil.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.model.LocationsModel
import com.alicimsamil.weatherapp.ui.main.MainScreenFragmentDirections

class MainScreenAdapter(private val mainClickListener: AdapterClickListener) :
    RecyclerView.Adapter<MainViewHolder>() {
    var locations = listOf<LocationsModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_recycler_shape, parent, false)
        return MainViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val locationText = holder.itemView.findViewById<TextView>(R.id.locationTitle)
        val locationButton = holder.itemView.findViewById<ConstraintLayout>(R.id.locationButton)
        locationText.text = locations.get(position).title
        locationButton.setOnClickListener {
            mainClickListener.onItemClicked(locations.get(position).woeid.toString(),locations.get(position).title)
        }

    }

    override fun getItemCount(): Int {
        return locations.size
    }
}

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}