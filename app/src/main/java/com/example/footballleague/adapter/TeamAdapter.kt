package com.example.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.footballleague.R
import com.example.footballleague.model.Team
import kotlinx.android.synthetic.main.item_league.view.*
import kotlin.properties.Delegates

class TeamAdapter(private val listener: (Team) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private var teams: List<Team> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        val viewHolder = ViewHolder(itemRow)
        viewHolder.itemView.setOnClickListener {
            listener(teams[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bindItem(teams[position])
        }
    }

    fun updateData(newList: List<Team>) {
        teams = newList
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvName: TextView = itemView.tvItemName
        private var ivLogo: ImageView = itemView.ivItemLogo

        fun bindItem(item: Team) {
            tvName.text = item.nameTeam
            Glide.with(itemView.context).load(item.teamBadge).into(ivLogo)
        }
    }
}