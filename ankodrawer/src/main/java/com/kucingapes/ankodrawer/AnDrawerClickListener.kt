/*
 * AnDrawerClickListenerstener.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 6:13 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.os.Handler
import android.support.v4.widget.DrawerLayout

interface AnDrawerClickListener {

    fun onDrawerClick(drawerLayout: DrawerLayout?, position: Int, anDrawerItem: AnDrawerItem) {
        if (drawerLayout != null) {
            Handler().postDelayed({
                drawerLayout.closeDrawers()
            }, 50)
        }
    }

}