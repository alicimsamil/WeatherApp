package com.alicimsamil.weatherapp.util

import android.app.AlertDialog
import android.content.Context
import com.alicimsamil.weatherapp.R
import kotlin.system.exitProcess

//Alert Dialog that will be displayed on the screen in case of no internet

fun internetAlertDialogShow(context: Context){


    val alertDialog = AlertDialog.Builder(context)
    alertDialog.setTitle(context.getString(R.string.internetStatus))
    alertDialog.setMessage(context.getString(R.string.internetFail))
    alertDialog.setPositiveButton(context.getString(R.string.exit)) { dialog, which ->
        exitProcess(-1)
    }
    alertDialog.show()



}