package com.alancamargo.dogz.core.tools

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DialogueHelperImpl(private val context: Context) : DialogueHelper {

    override fun showNeutralDialogue(
        titleRes: Int,
        messageRes: Int,
        dismissButtonTextRes: Int,
        iconRes: Int
    ) {
        MaterialAlertDialogBuilder(context).setTitle(titleRes)
            .setMessage(messageRes)
            .setIcon(iconRes)
            .setNeutralButton(dismissButtonTextRes, null)
            .show()
    }

}
