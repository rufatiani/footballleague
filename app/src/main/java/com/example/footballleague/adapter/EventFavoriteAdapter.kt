package com.example.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import kotlinx.android.synthetic.main.item_event.view.tvItemDateEventFav
import kotlinx.android.synthetic.main.item_event_favorite.view.*
import kotlin.properties.Delegates

class EventFavoriteAdapter(private val listener: (HashMap<String, Any>) -> Unit) :
    RecyclerView.Adapter<EventFavoriteAdapter.ViewHolder>() {

    private var events: List<Event> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event_favorite, parent, false)
        val viewHolder = ViewHolder(itemRow)
        viewHolder.itemView.setOnClickListener {
            val map = HashMap<String, Any>()
            map.put(Const.PARCEL_ACTION, Const.PARCEL_CLICK)
            map.put(Const.PARCEL_EVENT, events[viewHolder.adapterPosition])
            listener(map)
        }

        viewHolder.itemView.ivDeleteEventFav.setOnClickListener {
            val map = HashMap<String, Any>()
            map.put(Const.PARCEL_ACTION, Const.PARCEL_DELETE)
            map.put(Const.PARCEL_EVENT, events[viewHolder.adapterPosition])
            listener(map)
        }

        return viewHolder
    }

    override fun getItemCount(): Int = events.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            holder.bindItem(events[position])
        }
    }

    fun updateData(newList: List<Event>) {
        events = newList
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var tvNameLeague: TextView = itemView.tvItemNameLeagueFav
        private var tvNameEvent: TextView = itemView.tvItemNameEventFav
        private var tvDateEvent: TextView = itemView.tvItemDateEventFav

        fun bindItem(item: Event) {
            tvNameEvent.text = item.nameEvent
            tvNameLeague.text = item.nameLeague
            tvDateEvent.text = item.date
        }
    }

}