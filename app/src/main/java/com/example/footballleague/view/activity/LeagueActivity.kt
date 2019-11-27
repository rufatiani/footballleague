package com.example.footballleague.view.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.footballleague.R
import com.example.footballleague.adapter.PagerBottomNavigationAdapter
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_league.*

class LeagueActivity : AppCompatActivity() {

    lateinit var league: League
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        league = intent.getParcelableExtra(Const.PARCEL_LEAGUE)

        setContentView(R.layout.activity_league)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragmentAdapter =
            PagerBottomNavigationAdapter(league, baseContext, supportFragmentManager)
        vpLeague.adapter = fragmentAdapter
        vpLeague.offscreenPageLimit = 3
        vpLeague.currentItem = 0

        vpLeague.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    nav_view.menu.getItem(0).isChecked = false
                }

                nav_view.menu.getItem(p0).isChecked = true
                prevMenuItem = nav_view.menu.getItem(p0)
            }

        })

        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_detail -> {
                    vpLeague.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_prev -> {
                    vpLeague.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_next -> {
                    vpLeague.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
