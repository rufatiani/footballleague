package com.example.footballleague

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class DetailLeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val league: League = intent.getParcelableExtra("league")
        setLayout(league)
    }

    private fun setLayout(league : League){
        scrollView {
            verticalLayout {
                lparams(matchParent, wrapContent)
                padding = dip(16)

                imageView {
                    Glide.with(this@DetailLeagueActivity).load(league.path).into(this)
                }.lparams {
                    height = dip(100)
                    width = dip(100)
                    gravity = Gravity.CENTER
                }
                textView {
                    textSize = 20f
                    text = league.name
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    gravity = Gravity.CENTER
                    setMargins(0, dip(5), 0, dip(10))
                }
                textView {
                    textSize = 16f
                    text = league.decsription
                }.lparams {
                    gravity = Gravity.CENTER
                }
            }
        }
    }
}
