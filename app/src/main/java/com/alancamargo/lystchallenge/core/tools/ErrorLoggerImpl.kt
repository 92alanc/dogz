package com.alancamargo.lystchallenge.core.tools

import android.util.Log

private const val LOG_TAG = "APP_ERROR"

class ErrorLoggerImpl : ErrorLogger {

    override fun log(throwable: Throwable) {
        Log.e(LOG_TAG, throwable.message, throwable)
    }

}
