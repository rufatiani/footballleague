package com.example.footballleague.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleague.R
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.DetailEventActivity
import kotlinx.android.synthetic.main.item_event.view.*
import kotlin.properties.Delegates

class EventAdapter : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    private var events: List<Event> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemRow: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(itemRow)
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

        var tvNameEvent: TextView = itemView.tvItemNameEvent
        var tvNameLeague: TextView = itemView.tvItemNameLeague
        var tvDateEvent: TextView = itemView.tvItemDateEvent

        fun bindItem(item: Event) {
            tvNameEvent.text = item.nameEvent
            tvNameLeague.text = item.nameLeague
            tvDateEvent.text = item.date
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailEventActivity::class.java)
                intent.putExtra(Const.PARCEL_EVENT, item)
                itemView.context.startActivity(intent)
            }
        }
    }

}