/*
 * MainActivity.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 6:34 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankonavigationdrawer

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankodrawer.*
import com.kucingapes.ankodrawer.AnDrawerView.anDrawerLayout
import com.kucingapes.ankodrawer.AnDrawerView.anDrawerLayoutWithToolbar
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.coordinatorLayout

class MainActivity : AppCompatActivity(), AnDrawerClickListener {

    override fun onDrawerClick(identifier: Int) {
        super.onDrawerClick(identifier)
        when (identifier) {
            1 -> toast("wah")
            2 -> toast("gile")
            3 -> toast("lu")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drawer = AnDrawer(this, R.color.colorPrimary)
        frameLayout { anDrawerLayoutWithToolbar(drawer) }
        //frameLayout { anDrawerLayout(drawer) }
        AnDrawerInit.setupMainView(this, MainUi())
        AnDrawerInit.setupHeader(this, HeaderUi())
        AnDrawerInit.customToolbar(this, find(R.id.toolbar))

        drawer.setNavigationStyle(AnDrawerView.STYLE.NEW_MATERIAL)
        drawer.addItems().apply {
            val item1 = AnDrawerItem("Item 1")
                .addIcon(R.drawable.ic_emoticon)
                .addIdentifier(1)

            val item2 = AnDrawerItem("Item 2")
                .addIcon(R.drawable.ic_face)
                .addIdentifier(2)

            val item3 = AnDrawerItem("Item 3")
                .addIcon(R.drawable.ic_favorite)
                .addIdentifier(3)

            val item4 = AnDrawerItem("Item 4")
                .addIcon(R.drawable.ic_person)
                .addIdentifier(4)
                .setFocusable(false)

            val item5 = AnDrawerItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua")
                .addIcon(R.drawable.ic_emoticon)
                .addIdentifier(5)

            val item6 = AnDrawerItem("Item 6")
                .addIcon(R.drawable.ic_favorite)
                .addIdentifier(6)

            val divider = AnDrawerItem(AnDrawerItem.DIVIDER)

            add(divider)
            add(item1)
            add(item2)
            add(item3)
            add(item4)
            add(divider)
            add(item5)
            add(item6)
            drawer.setSelectedItem(3)

        }

    }

    class MainUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            coordinatorLayout {
                themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                    backgroundColorResource = R.color.colorPrimary
                    id = R.id.toolbar
                    title = context.getString(R.string.app_name)
                }.lparams(matchParent, dimenAttr(R.attr.actionBarSize))

                relativeLayout {
                    textView("MAIN VIEW").lparams { centerInParent() }
                }.lparams(matchParent, matchParent)
            }
        }
    }

    class HeaderUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            relativeLayout {
                lparams(matchParent, dip(200))
                backgroundColorResource = R.color.colorPrimary
                textView("CUSTOM HEADER") {
                    textColorResource = android.R.color.white
                    typeface = Typeface.DEFAULT_BOLD
                    textSize = 20f
                }.lparams { centerInParent() }
            }
        }
    }
}
