package com.example.footballleague.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footballleague.R
import com.example.footballleague.view.fragment.EventsFragment
import com.example.footballleague.view.fragment.FavoritesFragment
import com.example.footballleague.view.fragment.LeaguesFragment

class PagerBottomNavigationMainAdapter(
    val context: Context,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        LeaguesFragment(),
        EventsFragment(),
        FavoritesFragment()
    )

    override fun getItem(potition: Int): Fragment {
        return fragments[potition]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.title_league)
            1 -> context.resources.getString(R.string.title_event)
            else -> context.resources.getString(R.string.title_favorite)
        }
    }
}