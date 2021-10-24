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
        holder.itemView.findViewById<TextView>(R.id.dailyWeatherCelcius).text="${weather.get(position).the_temp.toInt()}°C"

        when(weather.get(position).weather_state_abbr){

            "sn" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.sn,context?.theme))
            "sl" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.sl,context?.theme))
            "h" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.h,context?.theme))
            "r" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.t,context?.theme))
            "hr" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.hr,context?.theme))
            "lr" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.lr,context?.theme))
            "s" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.s,context?.theme))
            "hc" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.hc,context?.theme))
            "lc" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.lc,context?.theme))
            "c" -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.c,context?.theme))

            else -> weatherIconImageView.setImageDrawable(context.resources.getDrawable(R.drawable.c,context?.theme))
        }
    }

    override fun getItemCount(): Int {
        return weather.size
    }

}

class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}