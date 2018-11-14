/*
 * AnDrawer.kt on AnkoNavigationDrawer
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
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.drawerLayout

object AnDrawer {

    object STYLE {
        const val DEFAULT = 0
        const val NEW_MATERIAL = 1
        const val GOOGLE_KEEP = 2
    }

    private fun hideStatusbar(context: AppCompatActivity) {
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun anWithCustomToolbar(activity: AppCompatActivity, toolbar: Toolbar?) {
        hideStatusbar(activity)
        activity.setSupportActionBar(toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_menu)
        toolbar?.setNavigationOnClickListener {
            anOpenDrawer(activity)
        }
    }

    fun anSetupMainView(context: AppCompatActivity, ui: AnkoComponent<ViewGroup>) {
        val rootView = context.find(R.id.main_container) as ViewGroup
        val mainView = ui.createView(AnkoContext.create(rootView.context, rootView))
        rootView.addView(mainView)
    }

    fun anSetupHeader(context: AppCompatActivity, ui: AnkoComponent<ViewGroup>) {
        val view = context.find(R.id.header_navigation) as ViewGroup
        val header = ui.createView(AnkoContext.create(view.context, view))
        view.addView(header)
    }

    fun anOpenDrawer(context: AppCompatActivity) {
        val drawer = context.find(R.id.drawer_layout) as DrawerLayout
        drawer.openDrawer(Gravity.START)
    }

    fun anCloseDrawer(context: AppCompatActivity) {
        val drawer = context.find(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(Gravity.START)) {
            drawer.closeDrawer(Gravity.START)
        }
    }

    fun anGetStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun ViewManager.anDrawerLayout(anDrawerAdapter: AnDrawerAdapter) = drawerLayout {
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
                    adapter = anDrawerAdapter
                }
            }
        }.lparams(matchParent, matchParent) {
            gravity = Gravity.START
        }
    }

    fun ViewManager.anDrawerLayoutWithStatusBar(anDrawerAdapter: AnDrawerAdapter) = drawerLayout {
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
                    }.lparams(matchParent, anGetStatusBarHeight(context))
                }
                recyclerView {
                    lparams(matchParent, matchParent)
                    id = R.id.drawer_item_list
                    layoutManager = LinearLayoutManager(context)
                    adapter = anDrawerAdapter
                }
            }
        }.lparams(matchParent, matchParent) {
            gravity = Gravity.START
        }
    }
}