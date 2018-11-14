/*
 * AnDrawerAdapterer.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 5:22 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kucingapes.ankodrawer.styleUi.DrawerUiDefault
import com.kucingapes.ankodrawer.styleUi.DrawerUiKeep
import com.kucingapes.ankodrawer.styleUi.DrawerUiMaterial
import org.jetbrains.anko.*
import android.support.v7.app.AppCompatDelegate



class AnDrawerAdapter(private val context: AppCompatActivity,
                      private var items: MutableList<AnDrawerItem>,
                      private var listener: AnDrawerClickListener) : RecyclerView.Adapter<AnDrawerAdapter.Holder>() {


    private var selectedItem = 0
    private var navigationStyle = 0
    private var colorTheme = AnDrawerUtils.fetchResourceColor(context.baseContext, R.attr.colorPrimary)

    fun setSelected(selectedItem: Int) : Int {
        this.selectedItem = selectedItem
        return selectedItem
    }

    fun setNavigationStyle(navigationStyle: Int) : Int {
        this.navigationStyle = navigationStyle
        return navigationStyle
    }

    fun setColorTheme(colorTheme: Int) : Int {
        this.colorTheme = colorTheme
        return colorTheme
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = when (navigationStyle) {
            0 -> DrawerUiDefault(colorTheme).createView(AnkoContext.create(context, parent))
            1 -> DrawerUiMaterial(colorTheme).createView(AnkoContext.create(context, parent))
            else -> DrawerUiKeep(colorTheme).createView(AnkoContext.create(context, parent))
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

        val drawerLayout = context.find<DrawerLayout>(R.id.drawer_layout)
        containerItem.setOnClickListener {
            if (item.focus) {
                selectedItem = position
            }
            listener.onDrawerClick(drawerLayout, position, item)
            notifyDataSetChanged()
        }

        when (selectedItem) {
            position -> {
                when (navigationStyle) {
                    0, 1 -> {
                        containerItem.setCardBackgroundColor(Color.parseColor("#201d1d1d"))
                        textItem.textColorResource = colorTheme
                        iconItem.apply {
                            setColorFilter(ContextCompat.getColor(context, colorTheme),
                                android.graphics.PorterDuff.Mode.SRC_ATOP)
                        }
                    }
                    2 -> {
                        val colorString = context.resources.getString(colorTheme)
                        val lastChar = colorString.substring(colorString.length - 6)
                        val withAlpha = "#33$lastChar"
                        containerItem.setCardBackgroundColor(Color.parseColor(withAlpha))
                        textItem.textColor = Color.parseColor("#1d1d1d")
                        iconItem.apply {
                            setColorFilter(Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.SRC_ATOP)
                        }
                    }
                }

            }

            else -> {
                when (navigationStyle) {
                    0, 1 -> {
                        textItem.textColor = Color.parseColor("#1d1d1d")

                        iconItem.apply {
                            setColorFilter(Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.MULTIPLY)
                        }
                    }
                    2 -> {
                        textItem.textColor = Color.parseColor("#1d1d1d")
                        iconItem.apply {
                            setColorFilter(Color.parseColor("#1d1d1d"),
                                android.graphics.PorterDuff.Mode.MULTIPLY)
                        }
                    }
                }
                containerItem.setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            }
        }
    }

    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView)

    fun visibility(view: View, visibility: Int) {
        when (visibility) {
            GONE -> view.visibility = View.GONE
            VISIBLE -> view.visibility = View.VISIBLE
        }
    }


    companion object {
        const val GONE = 0
        const val VISIBLE = 1

        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}