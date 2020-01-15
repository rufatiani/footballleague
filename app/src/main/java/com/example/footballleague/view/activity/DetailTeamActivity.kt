package com.example.footballleague.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.Team
import com.example.footballleague.utils.Const
import com.example.footballleague.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.fragment_detail_league.*
import org.koin.android.viewmodel.ext.android.viewModel


class DetailTeamActivity : AppCompatActivity(){
    private lateinit var team: Team
    private val teamViewModel: TeamViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        team = intent.getParcelableExtra(Const.PARCEL_TEAM)
        setContentView(R.layout.activity_detail_team)
        setLayout()

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setLayout() {
        tvDetailNameTeam.text = team.nameTeam
        tvDetailLeagueTeam.text = team.nameLeague
        tvDetailCountryTeam.text = team.country
        tvDetailDescriptionTeam.text = team.description

        if(team.youtube.isEmpty()) {
            tvDetailYoutubeTeam.text = "-"
        }else{
            tvDetailYoutubeTeam.text = team.youtube
        }

        if (team.website.isEmpty()) {
            tvDetailWebsiteTeam.text = "-"
        }else{
            tvDetailWebsiteTeam.text = team.website
        }

        Glide.with(this)
            .load(this.team.teamBadge)
            .error(R.drawable.ic_broken_image_primary_24dp)
            .into(ivDetailTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val searchTeam = menu?.findItem(R.id.search)
        if (searchTeam != null) {
            searchTeam.isVisible = false
        }

        val favoriteTeam = menu?.findItem(R.id.favorite)
        if (favoriteTeam != null) {
            favoriteTeam.isVisible = true
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.favorite) {
            teamViewModel.saveFavTeam(team)

            teamViewModel.idSave.observe(this, Observer { idSave ->
                if (idSave != null) {
                    Toast.makeText(
                        baseContext,
                        resources.getString(R.string.msg_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            teamViewModel.error.observe(this, Observer { msgError ->
                Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
            })
        }

        return super.onOptionsItemSelected(item)
    }
}