package com.example.footballleague.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.LeagueAdapter
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLayout()
        initData()
    }

    private fun setLayout() {
        rvLeagueList.layoutManager = LinearLayoutManager(this)
        rvLeagueList.adapter = LeagueAdapter(this, leagues) {
            startActivity<DetailLeagueActivity>(Const.PARCEL_LEAGUE to it)
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
                    logos.getResourceId(i, 0)
                )
            )
        }

        logos.recycle()
    }
}
