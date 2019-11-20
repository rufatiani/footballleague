package com.example.footballleague.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleague.R
import com.example.footballleague.adapter.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vpMain.adapter = PagerAdapter(this, supportFragmentManager)
        tlMain.setupWithViewPager(vpMain)
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

        return super.onPrepareOptionsMenu(menu)
    }
}
