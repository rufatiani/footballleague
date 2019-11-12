package com.example.footballleague.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.activity_detail_league.*

class DetailLeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        val league: League = intent.getParcelableExtra(Const.PARCEL_LEAGUE)
        setLayout(league)
    }

    private fun setLayout(league: League) {
        tvDetailName.text = league.name
        tvDetailDesc.text = league.decsription
        Glide.with(this@DetailLeagueActivity).load(league.path).into(ivDetailLogo)
    }
}
