package com.example.footballleague.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.activity_detail_event.*
import java.text.SimpleDateFormat
import java.util.*

class DetailEventActivity : AppCompatActivity() {
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        event = intent.getParcelableExtra(Const.PARCEL_EVENT)
        setContentView(R.layout.activity_detail_event)
        setLayout()

        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("SetTextI18n")
    private fun setLayout() {
        tvDetailNameEvent.text = event.nameEvent
        tvDetailLeagueEvent.text = event.nameLeague
        tvDetailDateEvent.text = event.date + " " + event.time

        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        tvDetailTimeEvent.text = sdf.format(sdf.parse(event.date + " " + event.time))

        tvDetailTeamHomeEvent.text = event.homeTeam
        tvDetailTeamAwayEvent.text = event.awayTeam

        if (event.homeFormation == null) {
            tvDetailFormationHomeEvent.text = "-"
        } else {
            tvDetailFormationHomeEvent.text = event.homeFormation
        }

        if (event.awayFormation == null) {
            tvDetailFormationAwayEvent.text = "-"
        } else {
            tvDetailFormationAwayEvent.text = event.homeFormation
        }

        tvDetailScoreHomeEvent.text = event.homeScore.toString()
        tvDetailScoreAwayEvent.text = event.awayScore.toString()

        if (event.imageUrl == null) {
            Glide.with(this).load(R.drawable.ic_broken_image_primary_24dp).into(ivDetailEvent)
        } else {
            Glide.with(this).load(this.event.imageUrl).into(ivDetailEvent)
        }
    }
}