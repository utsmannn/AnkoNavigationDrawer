/*
 * AnDrawerAdapterer.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 5:22 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.*
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.view.WindowManager
import com.kucingapes.ankodrawer.styleUi.*

class AnDrawer(private val listener: AnDrawerClickListener, private val colorPrimary: Int) :
    RecyclerView.Adapter<AnDrawer.Holder>() {

    private lateinit var context: Context
    private val items: MutableList<AnDrawerItem> = mutableListOf()

    private var selectedItem = 1
    private var navigationStyle = 0
    private var assetFont = ""
    private var drawerStatusBar = 0

    fun setNavigationStyle(navigationStyle: Int): Int {
        this.navigationStyle = navigationStyle
        return navigationStyle
    }

    fun addItems(): MutableList<AnDrawerItem> {
        return items
    }

    fun setSelectedItem(selectedItem: Int): Int {
        this.selectedItem = selectedItem
        return selectedItem
    }

    fun setFont(assetFont: String): String {
        this.assetFont = assetFont
        return assetFont
    }

    fun setDrawerStatusBar(drawerStatusBar: Int): Int {
        this.drawerStatusBar = drawerStatusBar
        return drawerStatusBar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = when (navigationStyle) {
            0 -> {
                if (assetFont != "") {
                    DrawerUiDefaultFont(colorPrimary, assetFont).createView(AnkoContext.create(context, parent))
                } else {
                    DrawerUiDefault(colorPrimary).createView(AnkoContext.create(context, parent))
                }
            }
            1 -> {
                if (assetFont != "") {
                    DrawerUiMaterialFont(colorPrimary, assetFont).createView(AnkoContext.create(context, parent))
                } else {
                    DrawerUiMaterial(colorPrimary).createView(AnkoContext.create(context, parent))
                }
            }
            else -> {
                if (assetFont != "") {
                    DrawerUiKeepFont(colorPrimary, assetFont).createView(AnkoContext.create(context, parent))
                } else {
                    DrawerUiKeep(colorPrimary).createView(AnkoContext.create(context, parent))
                }
            }
        }

        return Holder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        val item = items[position]

        val containerItem: CardView = holder.itemView.find(R.id.drawer_card_container)
        val iconItem: ImageView = holder.itemView.find(R.id.drawer_item_icon)
        val textItem: TextView = holder.itemView.find(R.id.drawer_item_text)
        val divider: View = holder.itemView.find(R.id.drawer_divider)

        if (item.divider) {
            visibility(containerItem, GONE)
            visibility(iconItem, GONE)
            visibility(textItem, GONE)
            visibility(divider, VISIBLE)
        } else {
            visibility(containerItem, VISIBLE)
            visibility(iconItem, VISIBLE)
            visibility(textItem, VISIBLE)
            visibility(divider, GONE)
        }

        iconItem.setImageResource(item.icon)
        textItem.text = item.title

        val drawerLayout = (context as AppCompatActivity).find<DrawerLayout>(R.id.drawer_layout)
        val statusBar = (context as AppCompatActivity).find(R.id.drawerStatusBar) as View?


        if (drawerStatusBar != 0) {
            statusBar?.backgroundColorResource = drawerStatusBar
        }
        containerItem.setOnClickListener {
            if (!item.divider && item.focus) {
                selectedItem = item.identifier
            }
            listener.onDrawerClick(item.identifier)
            Handler().postDelayed({
                drawerLayout.closeDrawers()
            }, 50)
            notifyDataSetChanged()
        }

        when (selectedItem) {
            item.identifier -> {
                when (navigationStyle) {
                    0, 1 -> {
                        containerItem.setCardBackgroundColor(Color.parseColor("#201d1d1d"))
                        textItem.textColorResource = colorPrimary
                        iconItem.apply {
                            setColorFilter(
                                ContextCompat.getColor(context, colorPrimary),
                                android.graphics.PorterDuff.Mode.SRC_ATOP
                            )
                        }
                    }
                    2 -> {
                        val colorString = context.resources.getString(colorPrimary)
                        val lastChar = colorString.substring(colorString.length - 6)
                        val withAlpha = "#33$lastChar"
                        containerItem.setCardBackgroundColor(Color.parseColor(withAlpha))
                        textItem.textColor = Color.parseColor("#1d1d1d")
                        iconItem.apply {
                            setColorFilter(
                                Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.SRC_ATOP
                            )
                        }
                    }
                }

            }

            else -> {
                when (navigationStyle) {
                    0, 1 -> {
                        textItem.textColor = Color.parseColor("#1d1d1d")
                        iconItem.apply {
                            setColorFilter(
                                Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.MULTIPLY
                            )
                        }
                    }
                    2 -> {
                        textItem.textColor = Color.parseColor("#1d1d1d")
                        iconItem.apply {
                            setColorFilter(
                                Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.MULTIPLY
                            )
                        }
                    }
                }
                containerItem.setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    private fun visibility(view: View, visibility: Int) {
        when (visibility) {
            GONE -> view.visibility = View.GONE
            VISIBLE -> view.visibility = View.VISIBLE
        }
    }

    private fun hideStatusbar(context: AppCompatActivity) {
        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    companion object {
        const val GONE = 0
        const val VISIBLE = 1

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}