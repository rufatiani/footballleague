package com.example.footballleague.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.LeagueAdapter
import com.example.footballleague.adapter.PagerAdapter
import com.example.footballleague.model.League
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpMain.adapter = PagerAdapter(this,supportFragmentManager)
        tlMain.setupWithViewPager(vpMain)
    }
}
