package com.example.footballleague.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.footballleague.R
import com.example.footballleague.view.fragment.EventsFragment
import com.example.footballleague.view.fragment.NextEventsFragment
import com.example.footballleague.view.fragment.PrevEventsFragment
import com.example.footballleague.view.fragment.PrevFavEventsFragment

class PagerAdapterFavorites(private val context: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = listOf(
        PrevFavEventsFragment(),
        PrevFavEventsFragment()
    )

    override fun getItem(potition: Int): Fragment {
        return fragments[potition]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.resources.getString(R.string.var_item_prev)
            else -> return context.resources.getString(R.string.var_item_next)
        }
    }
}