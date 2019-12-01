package com.example.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import kotlinx.android.synthetic.main.item_league.view.*
import kotlin.properties.Delegates

class LeagueAdapter(private val listener: (League) -> Unit) :
    RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagues: List<League> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        val viewHolder = ViewHolder(itemRow)
        viewHolder.itemView.setOnClickListener {
            listener(leagues[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = leagues.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bindItem(leagues[position])
        }
    }

    fun updateData(newList: List<League>) {
        leagues = newList
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvName: TextView = itemView.tvItemName
        private var ivLogo: ImageView = itemView.ivItemLogo

        fun bindItem(item: League) {
            tvName.text = item.name
            Glide.with(itemView.context).load(item.path).into(ivLogo)
        }
    }

}