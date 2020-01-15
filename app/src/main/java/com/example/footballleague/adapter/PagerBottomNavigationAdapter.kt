package com.example.footballleague.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footballleague.R
import com.example.footballleague.model.League
import com.example.footballleague.view.fragment.*

class PagerBottomNavigationAdapter(
    val league: League,
    val context: Context,
    fragmentManager: FragmentManager
) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        DetailLeagueFragment(league),
        ClassementFragment(league.idLeague),
        TeamsFragment(league.idLeague),
        PrevEventsFragment(league.idLeague),
        NextEventsFragment(league.idLeague)

    )

    override fun getItem(potition: Int): Fragment {
        return fragments[potition]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.var_item_detail)
            1 ->  context.resources.getString(R.string.var_item_classement)
            2 -> context.resources.getString(R.string.var_item_team)
            3 -> context.resources.getString(R.string.var_item_prev)
            else -> context.resources.getString(R.string.var_item_next)
        }
    }
}