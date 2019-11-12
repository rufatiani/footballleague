package com.example.footballleague

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout
import org.jetbrains.anko.wrapContent
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    private var leagues: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setLayout()
        initData()
    }

    private fun setLayout(){
        verticalLayout{
            lparams(matchParent, wrapContent)

            recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = LeagueAdapter(context, leagues) {
                    startActivity<DetailLeagueActivity>( "league" to it)
                }
            }
        }
    }

    private fun initData() {
        val ids = resources.getStringArray(R.array.league_id)
        val names = resources.getStringArray(R.array.league_name)
        val logos = resources.obtainTypedArray(R.array.league_logo)
        val descriptions = resources.getStringArray(R.array.league_desc)
        leagues.clear()
        for (i in names.indices) {
            leagues.add(League(ids[i], names[i], descriptions[i], logos.getResourceId(i, 0)))
        }

        logos.recycle()
    }
}
