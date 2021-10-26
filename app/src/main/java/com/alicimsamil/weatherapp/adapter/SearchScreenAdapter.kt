package com.alicimsamil.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.model.CityLocationModel
import com.alicimsamil.weatherapp.ui.search.SearchScreenFragmentDirections

class SearchScreenAdapter : RecyclerView.Adapter<SearchViewHolder>() {
    var locations = listOf<CityLocationModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.location_recycler_shape,parent,false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val locationText= holder.itemView.findViewById<TextView>(R.id.locationTitle)
        val locationButton = holder.itemView.findViewById<ConstraintLayout>(R.id.locationButton)
        locationText.text=locations.get(position).title
        locationButton.setOnClickListener {
            val action= SearchScreenFragmentDirections.actionSearchScreenFragmentToDetailScreenFragment(locations.get(position).woeid.toString(),locations.get(position).title)
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return locations.size
    }
}

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}