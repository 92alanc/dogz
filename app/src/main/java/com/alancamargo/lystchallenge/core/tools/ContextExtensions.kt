package com.alancamargo.lystchallenge.core.tools

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

fun <T : AppCompatActivity> Context.createIntent(clazz: KClass<T>): Intent {
    return Intent(this, clazz.java)
}
