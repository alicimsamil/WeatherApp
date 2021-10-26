package com.alicimsamil.weatherapp.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import com.alicimsamil.weatherapp.R

@SuppressLint("UseCompatLoadingForDrawables")
fun weatherIconPicker(weatherAbbreviation: String, context: Context): Drawable {

    val weatherDrawable: Drawable = when (weatherAbbreviation) {
        "sn" -> context.resources.getDrawable(R.drawable.sn, context.theme)
        "sl" -> context.resources.getDrawable(R.drawable.sl, context.theme)
        "h" -> context.resources.getDrawable(R.drawable.h, context.theme)
        "r" -> context.resources.getDrawable(R.drawable.t, context.theme)
        "hr" -> context.resources.getDrawable(R.drawable.hr, context.theme)
        "lr" -> context.resources.getDrawable(R.drawable.lr, context.theme)
        "s" -> context.resources.getDrawable(R.drawable.s, context.theme)
        "hc" -> context.resources.getDrawable(R.drawable.hc, context.theme)
        "lc" -> context.resources.getDrawable(R.drawable.lc, context.theme)
        "c" -> context.resources.getDrawable(R.drawable.c, context.theme)
        else -> context.resources.getDrawable(R.drawable.c, context.theme)
    }

    return weatherDrawable
}