package com.example.footballleague.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import com.example.footballleague.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.activity_detail_event.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class DetailEventActivity : AppCompatActivity() {
    private lateinit var event: Event
    private val eventViewModel: EventViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        event = intent.getParcelableExtra(Const.PARCEL_EVENT)
        setContentView(R.layout.activity_detail_event)
        setLayout()

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setLayout() {
        tvDetailNameEvent.text = event.nameEvent
        tvDetailLeagueEvent.text = event.nameLeague
        tvDetailDateEvent.text = String.format("%s %s", event.date, event.time)

        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
        tvDetailTimeEvent.text =
            sdf.format(sdf.parse(String.format("%s %s", event.date, event.time)))

        tvDetailTeamHomeEvent.text = event.homeTeam
        tvDetailTeamAwayEvent.text = event.awayTeam

        if (event.homeFormation.isNullOrEmpty()) {
            tvDetailFormationHomeEvent.text = "-"
        } else {
            tvDetailFormationHomeEvent.text = event.homeFormation
        }

        if (event.awayFormation.isNullOrEmpty()) {
            tvDetailFormationAwayEvent.text = "-"
        } else {
            tvDetailFormationAwayEvent.text = event.homeFormation
        }

        tvDetailScoreHomeEvent.text = event.homeScore.toString()
        tvDetailScoreAwayEvent.text = event.awayScore.toString()

        Glide.with(this)
            .load(this.event.imageUrl)
            .error(R.drawable.ic_broken_image_primary_24dp)
            .into(ivDetailEvent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val searchEvent = menu?.findItem(R.id.search)
        if (searchEvent != null) {
            searchEvent.isVisible = false
        }

        val favoriteEvent = menu?.findItem(R.id.favorite)
        if (favoriteEvent != null) {
            favoriteEvent.isVisible = true
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.favorite) {
            eventViewModel.saveFavEvent(event)

            eventViewModel.idSave.observe(this, Observer { idSave ->
                if (idSave != null) {
                    Toast.makeText(
                        baseContext,
                        resources.getString(R.string.msg_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            eventViewModel.error.observe(this, Observer { msgError ->
                Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
            })
        }

        return super.onOptionsItemSelected(item)
    }
}