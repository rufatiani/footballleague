package com.example.footballleague.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.footballleague.R
import com.example.footballleague.adapter.LeagueAdapter
import com.example.footballleague.model.League
import kotlinx.android.synthetic.main.fragment_leagues.*

class LeaguesFragment : Fragment() {

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var leagueAdapter : LeagueAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_leagues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        setLayout()
    }

    private fun setLayout() {
        leagueAdapter = LeagueAdapter()
        leagueAdapter.updateData(leagues)
        rvLeagueList.apply {
            layoutManager = LinearLayoutManager(this@LeaguesFragment.context)
            adapter = leagueAdapter
        }
    }

    private fun initData() {
        val ids = resources.getStringArray(R.array.league_id)
        val names = resources.getStringArray(R.array.league_name)
        val logos = resources.obtainTypedArray(R.array.league_logo)
        val descriptions = resources.getStringArray(R.array.league_desc)
        leagues.clear()
        for (i in names.indices) {
            leagues.add(
                League(
                    ids[i],
                    names[i],
                    descriptions[i],
                    "",
                    "",
                    logos.getResourceId(i, 0)
                )
            )
        }

        logos.recycle()
    }
}
