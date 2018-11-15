/*
 * AnDrawerView.ktw.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 6:48 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.content.Context
import android.graphics.Color
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout

object AnDrawerView {

    object STYLE {
        const val DEFAULT = 0
        const val NEW_MATERIAL = 1
        const val GOOGLE_KEEP = 2
    }

    fun ViewManager.anDrawerLayout(anDrawer: AnDrawer) = drawerLayout {
        id = R.id.drawer_layout
        frameLayout {
            id = R.id.main_container
        }.lparams(matchParent, matchParent)
        navigationView {
            id = R.id.navigation_view
            verticalLayout {
                frameLayout {
                    id = R.id.header_navigation
                }.lparams(matchParent, wrapContent)
                recyclerView {
                    lparams(matchParent, matchParent)
                    id = R.id.drawer_item_list
                    layoutManager = LinearLayoutManager(context)
                    adapter = anDrawer
                }
            }
        }.lparams(matchParent, matchParent) {
            gravity = Gravity.START
        }
    }

    fun ViewManager.anDrawerLayoutFakeStatusBar(anDrawer: AnDrawer) = drawerLayout {
        id = R.id.drawer_layout
        frameLayout {
            id = R.id.main_container
        }.lparams(matchParent, matchParent)

        navigationView {
            id = R.id.navigation_view
            verticalLayout {
                relativeLayout {
                    frameLayout {
                        id = R.id.header_navigation
                    }.lparams(matchParent, wrapContent)
                    view {
                        backgroundColor = Color.parseColor("#20000000")
                    }.lparams(matchParent, AnDrawerInit.anGetStatusBarHeight(context))
                }
                recyclerView {
                    lparams(matchParent, matchParent)
                    id = R.id.drawer_item_list
                    layoutManager = LinearLayoutManager(context)
                    adapter = anDrawer
                }
            }
        }.lparams(matchParent, matchParent) {
            gravity = Gravity.START
        }
    }
}