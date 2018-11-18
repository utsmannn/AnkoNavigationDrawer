/*
 * AnDrawerInit.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/15/18 12:03 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

object AnDrawerInit {
    private fun hideStatusbar(context: AppCompatActivity) {
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun customToolbar(activity: AppCompatActivity, toolbar: Toolbar?) {
        hideStatusbar(activity)
        activity.setSupportActionBar(toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_menu)
        toolbar?.setNavigationOnClickListener {
            openDrawer(activity)
        }
    }

    fun customToolbar(activity: AppCompatActivity, toolbar: Toolbar?, icon: Int?) {
        hideStatusbar(activity)
        activity.setSupportActionBar(toolbar)

        if (icon == null) toolbar?.setNavigationIcon(R.drawable.ic_menu)
        else toolbar?.setNavigationIcon(icon)

        toolbar?.setNavigationOnClickListener {
            openDrawer(activity)
        }
    }

    fun customToolbar(activity: AppCompatActivity, toolbar: Toolbar?, drawable: BitmapDrawable?) {
        hideStatusbar(activity)
        activity.setSupportActionBar(toolbar)

        if (drawable == null) toolbar?.setNavigationIcon(R.drawable.ic_menu)
        else toolbar?.navigationIcon = drawable

        toolbar?.setNavigationOnClickListener {
            openDrawer(activity)
        }
    }

    fun setupMainView(context: AppCompatActivity, ui: AnkoComponent<ViewGroup>) {
        val rootView = context.find(R.id.main_container) as ViewGroup
        val mainView = ui.createView(AnkoContext.create(rootView.context, rootView))
        rootView.addView(mainView)
    }

    fun setupHeader(context: AppCompatActivity, ui: AnkoComponent<ViewGroup>) {
        val view = context.find(R.id.header_navigation) as ViewGroup
        val header = ui.createView(AnkoContext.create(view.context, view))
        view.addView(header)
    }

    fun openDrawer(context: AppCompatActivity) {
        val drawer = context.find(R.id.drawer_layout) as DrawerLayout
        drawer.openDrawer(Gravity.START)
    }

    fun closeDrawer(context: AppCompatActivity) {
        val drawer = context.find(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START)
        }
    }

    fun setLightStatusBar(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = activity.window.decorView.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.decorView.systemUiVisibility = flags
            activity.window.statusBarColor = Color.WHITE
        }
    }

    internal fun anGetStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}