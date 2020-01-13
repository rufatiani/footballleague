package com.example.footballleague.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.EventAdapter
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.DetailEventActivity
import com.example.footballleague.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.android.viewmodel.ext.android.viewModel

class NextEventsFragment(private val idLeague: String) : Fragment() {
    private var events: List<Event> = ArrayList()
    private lateinit var eventAdapter: EventAdapter
    private val eventViewModel: EventViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        svEvents.visibility = View.GONE
        initData()
    }

    private fun setLayout() {
        eventAdapter = EventAdapter { itemSelected: Event ->
            val intent = Intent(context, DetailEventActivity::class.java)
            intent.putExtra(Const.PARCEL_EVENT, itemSelected)
            startActivity(intent)
        }
        eventAdapter.updateData(events)
        rvEventList.apply {
            layoutManager = LinearLayoutManager(this@NextEventsFragment.context)
            adapter = eventAdapter
        }
    }

    private fun initData() {
        eventViewModel.nextEvents?.observe(this, Observer { events ->
            if(events != null) {
                this.events = events
                setLayout()
            }
        })
        eventViewModel.loading.observe(this, Observer { loading ->
            pbEventList.visibility = if (loading) View.VISIBLE else View.GONE
        })
        eventViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        eventViewModel.loadNextEvents(idLeague)
    }
}