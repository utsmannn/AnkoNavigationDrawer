/*
 * DrawerUiKeep.kt on AnkoNavigationDrawer
 * Developed by Muhammad Utsman
 * Last modified 11/14/18 7:01 AM
 * Copyright (c) 2018 kucingapes
 */

package com.kucingapes.ankodrawer.styleUi

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.kucingapes.ankodrawer.AnDrawerUtils
import com.kucingapes.ankodrawer.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DrawerUiKeep(private val colorTheme: Int) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            cardView {
                lparams(matchParent, wrapContent) {
                    leftMargin = dip(-25)
                    rightMargin = dip(15)

                }
                cardElevation = 0f

                radius = 50f
                id = R.id.drawer_card_container
                setCardBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
                AnDrawerUtils.rippleThis(
                    this,
                    AnDrawerUtils.FOREGROUND
                )

                linearLayout {
                    imageView {
                        id = R.id.drawer_item_icon
                        setColorFilter(ContextCompat.getColor(context, colorTheme),
                            android.graphics.PorterDuff.Mode.MULTIPLY)

                    }.lparams(dip(25), dip(25)) {
                        margin = dip(12)
                        gravity = Gravity.CENTER_VERTICAL
                    }

                    textView {
                        id = R.id.drawer_item_text
                        AnDrawerUtils.materialText(this)
                        typeface = Typeface.DEFAULT_BOLD
                        textColorResource = colorTheme
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                    }.lparams(matchParent, wrapContent) {
                        leftMargin = dip(12)
                        gravity = Gravity.CENTER_VERTICAL
                    }
                }.lparams(matchParent, wrapContent) {
                    leftMargin = dip(25)
                }
            }

            view {
                id = R.id.drawer_divider
                backgroundColor = Color.parseColor("#3f797979")
            }.lparams(matchParent, dip(1)) {
                topMargin = dip(6)
                bottomMargin = dip(6)
                centerInParent()
            }
        }
    }
}