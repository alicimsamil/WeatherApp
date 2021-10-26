package com.alicimsamil.weatherapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.alicimsamil.weatherapp.R

fun weatherIconPicker(weatherAbbreviation : String, context:Context) : Drawable{

    var weatherDrawable : Drawable

    when(weatherAbbreviation){

        "sn" -> weatherDrawable = context.resources.getDrawable(R.drawable.sn,context?.theme)
        "sl" -> weatherDrawable =context.resources.getDrawable(R.drawable.sl,context?.theme)
        "h" -> weatherDrawable =context.resources.getDrawable(R.drawable.h,context?.theme)
        "r" -> weatherDrawable =context.resources.getDrawable(R.drawable.t,context?.theme)
        "hr" -> weatherDrawable =context.resources.getDrawable(R.drawable.hr,context?.theme)
        "lr" -> weatherDrawable =context.resources.getDrawable(R.drawable.lr,context?.theme)
        "s" -> weatherDrawable =context.resources.getDrawable(R.drawable.s,context?.theme)
        "hc" -> weatherDrawable =context.resources.getDrawable(R.drawable.hc,context?.theme)
        "lc" -> weatherDrawable =context.resources.getDrawable(R.drawable.lc,context?.theme)
        "c" -> weatherDrawable =context.resources.getDrawable(R.drawable.c,context?.theme)

        else -> weatherDrawable =context.resources.getDrawable(R.drawable.c,context?.theme)
    }

    return weatherDrawable


}