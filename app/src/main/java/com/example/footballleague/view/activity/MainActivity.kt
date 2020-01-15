package com.example.footballleague.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.footballleague.R
import com.example.footballleague.adapter.PagerBottomNavigationMainAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view2.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val fragmentAdapter =
            PagerBottomNavigationMainAdapter(baseContext, supportFragmentManager)
        vpMain.adapter = fragmentAdapter
        vpMain.offscreenPageLimit = 3
        vpMain.currentItem = 0

        vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem?.isChecked = false
                } else {
                    nav_view2.menu.getItem(0).isChecked = false
                }

                nav_view2.menu.getItem(p0).isChecked = true
                prevMenuItem = nav_view2.menu.getItem(p0)
            }

        })
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
            favoriteEvent.isVisible = false
        }

        return super.onPrepareOptionsMenu(menu)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_leagues -> {
                    vpMain.currentItem = 0
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_events -> {
                    vpMain.currentItem = 1
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_teams -> {
                    vpMain.currentItem = 2
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_favorites -> {
                    vpMain.currentItem = 3
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
