package com.example.footballleague.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.LeagueAdapter
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var leagueAdapter : LeagueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        setLayout()
    }

    private fun setLayout() {
        leagueAdapter = LeagueAdapter()
        leagueAdapter.updateData(leagues)
        rvLeagueList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
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
