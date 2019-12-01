package com.example.footballleague.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.model.Event
import kotlinx.android.synthetic.main.item_event.view.*
import kotlin.properties.Delegates

class EventAdapter(private val listener: (Event) -> Unit) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var events: List<Event> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        val viewHolder = ViewHolder(itemRow)
        viewHolder.itemView.setOnClickListener {
            listener(events[viewHolder.adapterPosition])
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

        private var tvNameEvent: TextView = itemView.tvItemNameEvent
        private var tvNameLeague: TextView = itemView.tvItemNameLeague
        private var tvDateEvent: TextView = itemView.tvItemDateEventFav

        fun bindItem(item: Event) {
            tvNameEvent.text = item.nameEvent
            tvNameLeague.text = item.nameLeague
            tvDateEvent.text = item.date
        }
    }

}