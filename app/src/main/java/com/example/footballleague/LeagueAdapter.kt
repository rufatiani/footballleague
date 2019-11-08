package com.example.footballleague

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext
import java.util.*

class LeagueAdapter(private val context: Context, private val leagues: List<League>, private val listener: (League) ->  Unit)
    : RecyclerView.Adapter<LeagueAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapter.ViewHolder {
        return ViewHolder(LeagueUI().createView(AnkoContext.create(context, parent)))
    }

    override fun getItemCount(): Int = leagues.size


    override fun onBindViewHolder(holder: LeagueAdapter.ViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        var tvName: TextView = itemView.findViewById(LeagueUI.tvName)
        var ivLogo: ImageView = itemView.findViewById(LeagueUI.ivLogo)

        fun bindItem(items: League, listener: (League) -> Unit) {
            tvName.text = items.name
            Glide.with(containerView).load(items.path).into(ivLogo)
            containerView.setOnClickListener { listener(items) }
        }
    }

}