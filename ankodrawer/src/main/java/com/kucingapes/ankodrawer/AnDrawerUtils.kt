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
import android.widget.ImageView
import android.widget.TextView
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable

object AnDrawerUtils {

    const val FOREGROUND = 0
    const val BACKGROUND = 1

    fun fetchResourceColor(context: Context, colorAttr: Int): Int {
        val typedValue = TypedValue()
        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(colorAttr))
        val color = a.getColor(0, 0)

        a.recycle()

        return color
    }

    fun setLightStatusBar(view: View, activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            activity.window.statusBarColor = Color.WHITE
        }
    }

    fun materialCard(cardView: CardView) {
        cardView.apply {
            radius = 8f
        }
    }

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