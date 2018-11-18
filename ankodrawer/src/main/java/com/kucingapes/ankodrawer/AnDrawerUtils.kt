/*
 * AnDrawerUtils.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 6:21 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.app.Activity
import android.content.Context

object AnDrawerUtils {

    const val FOREGROUND = 0
    const val BACKGROUND = 1

    fun materialText(textView: TextView) {
        textView.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                letterSpacing = 0.03f
            }
        }
    }

    fun rippleThis(view: View, type: Int) {
        view.apply {
            val rippleValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, rippleValue, true)

            when (type) {
                FOREGROUND -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    foreground = ContextCompat.getDrawable(context, rippleValue.resourceId)
                }
                BACKGROUND -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    background = ContextCompat.getDrawable(context, rippleValue.resourceId)
                }
            }
        }
    }
}