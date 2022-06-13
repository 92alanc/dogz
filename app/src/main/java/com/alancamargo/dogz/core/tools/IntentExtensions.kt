package com.alancamargo.dogz.core.tools

import android.content.Intent
import android.os.Parcelable

const val EXTRA_ARGUMENTS = "com.alancamargo.dogz.core.tools.ARGUMENTS"

fun <T : Parcelable> Intent.putArguments(args: T): Intent {
    return putExtra(EXTRA_ARGUMENTS, args)
}
