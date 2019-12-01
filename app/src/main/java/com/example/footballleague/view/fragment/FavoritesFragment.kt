package com.example.footballleague.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleague.R
import com.example.footballleague.adapter.PagerAdapterFavorites
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter =
            context?.let { fragmentManager?.let { it1 -> PagerAdapterFavorites(it, it1) } }
        vpFavorites.adapter = fragmentAdapter
        tlFavorites.setupWithViewPager(vpFavorites)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        val searchEvent = menu.findItem(R.id.search)
        if (searchEvent != null) {
            searchEvent.isVisible = false
        }

        val favoriteEvent = menu.findItem(R.id.favorite)
        if (favoriteEvent != null) {
            favoriteEvent.isVisible = false
        }
    }
}