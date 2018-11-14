/*
 * AnDrawerItemem.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 5:00 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

class AnDrawerItem {
    var icon: Int = -1
    var title: String = ""
    var divider: Boolean = false
    var identifier: Int = -1
    var tag: String = ""
    var focus: Boolean = true
    var isSelected: Boolean = false

    constructor(title: String) {
        this.title = title
    }

    constructor(icon: Int, title: String) {
        this.icon = icon
        this.title = title
    }

    constructor(divider: Boolean) {
        this.divider = divider
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

    fun isSelected(selectItem: Boolean) : AnDrawerItem {
        this.isSelected = selectItem
        return this
    }

    companion object {
        const val DIVIDER = true
    }
}