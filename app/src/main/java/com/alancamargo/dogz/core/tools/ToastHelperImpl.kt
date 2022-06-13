package com.alancamargo.dogz.core.tools

import android.content.Context
import android.widget.Toast

class ToastHelperImpl(private val context: Context) : ToastHelper {

    override fun showToast(messageRes: Int) {
        Toast.makeText(context, messageRes, Toast.LENGTH_SHORT).show()
    }

}
