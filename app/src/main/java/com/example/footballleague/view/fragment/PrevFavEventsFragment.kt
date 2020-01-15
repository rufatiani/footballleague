package com.example.footballleague.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.EventFavoriteAdapter
import com.example.footballleague.model.Event
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.DetailEventActivity
import com.example.footballleague.viewmodel.EventViewModel
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.android.viewmodel.ext.android.viewModel

class PrevFavEventsFragment : Fragment() {
    private lateinit var eventAdapter: EventFavoriteAdapter
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
        setLayout()
        initData()
    }

    private fun setLayout() {
        rvEventList.apply {
            layoutManager = LinearLayoutManager(this@PrevFavEventsFragment.context)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private val events = Observer<List<Event>> { events ->
        if (events != null) {
            pbEventList.visibility = View.VISIBLE
            eventAdapter = EventFavoriteAdapter { itemSelected: HashMap<String, Any> ->
                if (itemSelected[Const.PARCEL_ACTION]!!.equals(Const.PARCEL_CLICK)) {
                    val intent = Intent(activity, DetailEventActivity::class.java)
                    intent.putExtra(Const.PARCEL_EVENT, itemSelected[Const.PARCEL_EVENT] as Event)
                    startActivity(intent)
                } else {
                    showDeleteDialogue(itemSelected[Const.PARCEL_EVENT] as Event)?.show()
                }
            }

            eventAdapter.updateData(events)
            rvEventList.adapter = eventAdapter

            pbEventList.visibility = View.GONE
            rvEventList.adapter = eventAdapter
            eventAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        eventViewModel.prevFavEvents.observe(viewLifecycleOwner, this.events)

        eventViewModel.loading.observe(this, Observer { loading ->
            pbEventList.visibility = if (loading) View.VISIBLE else View.GONE
        })
        eventViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        eventViewModel.loadPrevFavEvents()
    }

    private fun showDeleteDialogue(event: Event): AlertDialog.Builder? {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle(resources.getString(R.string.var_msg_title))
        builder?.setMessage(resources.getString(R.string.var_msg))

        builder?.setPositiveButton(android.R.string.yes) { dialog, which ->
            eventViewModel.deleteFavEvent(event.idEvent)
            initData()
        }

        builder?.setNegativeButton(android.R.string.no) { dialog, which ->
            builder.setCancelable(true)
        }

        return builder
    }
}