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
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.item_event_favorite.view.*
import kotlinx.android.synthetic.main.item_league.view.*
import kotlinx.android.synthetic.main.item_league.view.ivItemLogo
import kotlinx.android.synthetic.main.item_league.view.tvItemName
import kotlinx.android.synthetic.main.item_team_favorite.view.*
import kotlin.properties.Delegates

class TeamFavoriteAdapter (private val listener: (HashMap<String, Any>) -> Unit) :
    RecyclerView.Adapter<TeamFavoriteAdapter.ViewHolder>() {

    private var teams: List<Team> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_team_favorite, parent, false)
        val viewHolder = ViewHolder(itemRow)
        viewHolder.itemView.setOnClickListener {
            val map = HashMap<String, Any>()
            map.put(Const.PARCEL_ACTION, Const.PARCEL_CLICK)
            map.put(Const.PARCEL_TEAM, teams[viewHolder.adapterPosition])
            listener(map)
        }

        viewHolder.itemView.ivDeleteTeamFav.setOnClickListener {
            val map = HashMap<String, Any>()
            map.put(Const.PARCEL_ACTION, Const.PARCEL_DELETE)
            map.put(Const.PARCEL_TEAM, teams[viewHolder.adapterPosition])
            listener(map)
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