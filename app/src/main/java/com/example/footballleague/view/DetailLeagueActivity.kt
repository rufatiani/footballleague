package com.example.footballleague.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import com.example.footballleague.viewmodel.LeagueViewModel
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailLeagueActivity : AppCompatActivity() {

    private val leagueViewModel: LeagueViewModel by viewModel()
    private lateinit var league : League

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)

        league = intent.getParcelableExtra(Const.PARCEL_LEAGUE)
        initViewModel(league.idLeague)
    }

    private fun setLayout(league: League) {
        tvDetailName.text = league.name
        tvDetailDesc.text = league.description
        Glide.with(this@DetailLeagueActivity).load(this.league.path).into(ivDetailLogo)
    }

    private fun initViewModel(idLeague : String) {
        leagueViewModel.league.observe(this, Observer { league ->
            setLayout(league)
        })
        leagueViewModel.loading.observe(this, Observer { loading ->
            pbLeagueDetail.visibility = if (loading) View.VISIBLE else View.GONE
        })
        leagueViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
        })
        leagueViewModel.loadLeagueDetail(idLeague)
    }
}
