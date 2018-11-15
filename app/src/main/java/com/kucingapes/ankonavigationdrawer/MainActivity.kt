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
import com.kucingapes.ankodrawer.AnDrawerView.anDrawerLayoutFakeStatusBar
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.design.coordinatorLayout

class MainActivity : AppCompatActivity(), AnDrawerClickListener{

    override fun onDrawerClick(drawerLayout: DrawerLayout?, position: Int, anDrawerItem: AnDrawerItem) {
        super.onDrawerClick(drawerLayout, position, anDrawerItem)
        when (anDrawerItem.identifier) {
            1 -> toast("item 1")
            2 -> toast("item 2")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val drawer = AnDrawer(this, R.color.colorPrimary)
        frameLayout { anDrawerLayoutFakeStatusBar(drawer) }
        AnDrawerInit.setupMainView(this, MainUi())
        AnDrawerInit.setupHeader(this, HeaderUi())
        AnDrawerInit.withCustomToolbar(this, find(R.id.toolbar))

        drawer.setNavigationStyle(AnDrawerView.STYLE.NEW_MATERIAL)
        drawer.addItems().apply {
            val item1 = AnDrawerItem(R.drawable.ic_person, "Item 1").addIdentifier(1)
            val item2 = AnDrawerItem(R.drawable.ic_face, "Item 2").addIdentifier(2)
            val item3 = AnDrawerItem(R.drawable.ic_favorite, "Item 3").addIdentifier(3)
            val item4 = AnDrawerItem(R.drawable.ic_train, "Item 4").addIdentifier(4)
            val item5 = AnDrawerItem(R.drawable.ic_emoticon, "Item 5").addIdentifier(5)
            val divider = AnDrawerItem(AnDrawerItem.DIVIDER)

            add(divider)
            add(item1)
            add(item2)
            add(item3)
            add(item4)
            add(divider)
            add(item5)
            drawer.setSelected(3)
        }

    }

    class MainUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            verticalLayout {
                view {
                    backgroundColorResource = R.color.colorPrimaryDark
                }.lparams(matchParent, AnDrawerInit.anGetStatusBarHeight(context))
                coordinatorLayout {
                    themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
                        backgroundColorResource = R.color.colorPrimary
                        id = R.id.toolbar
                        title = context.getString(R.string.app_name)
                    }.lparams(matchParent, dimenAttr(R.attr.actionBarSize))
                }
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
