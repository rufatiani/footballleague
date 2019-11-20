package com.example.footballleague.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.League
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.LeagueActivity
import kotlinx.android.synthetic.main.item_league.view.*
import kotlin.properties.Delegates

class LeagueAdapter : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    private var leagues: List<League> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        return ViewHolder(itemRow)
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
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, LeagueActivity::class.java)
                intent.putExtra(Const.PARCEL_LEAGUE, item)
                itemView.context.startActivity(intent)
            }
        }
    }

}