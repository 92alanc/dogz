package com.alancamargo.dogz.core.tools

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showNeutralDialogue(
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        @StringRes dismissButtonTextRes: Int,
        @DrawableRes iconRes: Int
    )

}
