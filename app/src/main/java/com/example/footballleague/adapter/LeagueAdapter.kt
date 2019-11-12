package com.example.footballleague.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(
    private val context: Context,
    private val leagues: List<League>,
    private val listener: (League) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return ViewHolder(itemRow)
    }

    override fun getItemCount(): Int = leagues.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        var tvName: TextView = itemView.tvItemName
        var ivLogo: ImageView = itemView.ivItemLogo

        fun bindItem(items: League, listener: (League) -> Unit) {
            tvName.text = items.name
            Glide.with(containerView).load(items.path).into(ivLogo)
            containerView.setOnClickListener { listener(items) }
        }
    }

}