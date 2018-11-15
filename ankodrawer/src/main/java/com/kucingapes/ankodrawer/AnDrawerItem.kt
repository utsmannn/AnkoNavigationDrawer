/*
 * AnDrawerItemem.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 5:00 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.view.View

class AnDrawerItem {
    internal var icon: Int = -1
    internal var title: String = ""
    internal var divider: Boolean = false
    var identifier: Int = -1
    internal var tag: String = ""
    internal var focus: Boolean = true
    internal var listener: View.OnClickListener? = null
    internal var onClickListener: AnDrawerClickListener? = null

    constructor(title: String) {
        this.title = title
    }

    constructor(divider: Boolean) {
        this.divider = divider
    }

    fun addIcon(icon: Int): AnDrawerItem {
        this.icon = icon
        return this
    }

    fun addIdentifier(identifier: Int): AnDrawerItem {
        this.identifier = identifier
        return this
    }

    fun addTag(tag: String): AnDrawerItem {
        this.tag = tag
        return this
    }

    fun setFocusable(focus: Boolean): AnDrawerItem {
        this.focus = focus
        return this
    }

    companion object {
        const val DIVIDER = true
    }
}