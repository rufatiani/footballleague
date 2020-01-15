package com.example.footballleague.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import com.example.footballleague.viewmodel.LeagueViewModel
import kotlinx.android.synthetic.main.fragment_detail_league.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailLeagueFragment(val league: League) : Fragment() {

    private val leagueViewModel: LeagueViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel(league.idLeague)
    }

    private fun setLayout(league: League) {
        tvDetailName.text = league.name
        tvDetailDesc.text = league.description
        activity?.let { Glide.with(it).load(this.league.path).into(ivDetailLogo) }
    }

    private fun initViewModel(idLeague: String?) {
        leagueViewModel.league.observe(this, Observer { league ->
            setLayout(league)
        })
        leagueViewModel.loading.observe(this, Observer { loading ->
            pbEventDetail.visibility = if (loading) View.VISIBLE else View.GONE
        })
        leagueViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        idLeague?.let { leagueViewModel.loadLeagueDetail(it) }
    }
}