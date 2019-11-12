package com.example.footballleague

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*

class LeagueUI : AnkoComponent<ViewGroup>{

    companion object{
        const val tvName = 1
        const val ivLogo = 2

    }

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        verticalLayout {
            this.orientation = LinearLayout.HORIZONTAL
            lparams(matchParent, wrapContent)
            padding = dip(16)

            imageView {
                id = ivLogo
            }.lparams {
                height = dip(40)
                width = dip(40)
                gravity = Gravity.CENTER
            }

            textView {
                id = tvName
                textSize = 16f
            }.lparams {
                margin = dip(10)
                gravity = Gravity.CENTER
            }
        }
    }

}