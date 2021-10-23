package com.alicimsamil.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R

class DetailScreenAdapter : RecyclerView.Adapter<DetailViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weather_recycler_shape,parent,false)
        return DetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}