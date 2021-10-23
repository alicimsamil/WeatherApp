package com.alicimsamil.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alicimsamil.weatherapp.R
import com.alicimsamil.weatherapp.model.ConsolidatedWeather

class DetailScreenAdapter(val context: Context) : RecyclerView.Adapter<DetailViewHolder>() {

    var weather = listOf<ConsolidatedWeather>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weather_recycler_shape,parent,false)
        return DetailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val weatherIconImageView=holder.itemView.findViewById<ImageView>(R.id.dailyWeatherIcon)
        holder.itemView.findViewById<TextView>(R.id.dailyWeatherCelcius).text=weather.get(position).the_temp.toInt().toString()+"°C"

        when(weather.get(position).weather_state_abbr){

            "sn" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.sn))
            "sl" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.sl))
            "h" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.h))
            "r" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.t))
            "hr" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.hr))
            "lr" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.lr))
            "s" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.s))
            "hc" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.hc))
            "lc" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.lc))
            "c" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.c))

            else -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.c))
        }
    }

    override fun getItemCount(): Int {
        return weather.size
    }

}

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}