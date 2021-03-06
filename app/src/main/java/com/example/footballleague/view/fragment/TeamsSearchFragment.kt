package com.example.footballleague.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballleague.R
import com.example.footballleague.adapter.TeamAdapter
import com.example.footballleague.model.Team
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.DetailTeamActivity
import com.example.footballleague.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_teams.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeamsSearchFragment : Fragment() {
    private var teams: List<Team> = ArrayList()
    private lateinit var teamAdapter: TeamAdapter
    private val teamViewModel: TeamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        svTeams.visibility = View.VISIBLE
        svTeams.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isEmpty()) {
                    initData(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    initData(query)
                }
                return true
            }
        })
        initData("")
    }

    private fun setLayout() {
        teamAdapter = TeamAdapter { itemSelected: Team ->
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra(Const.PARCEL_TEAM, itemSelected)
            startActivity(intent)
        }
        teamAdapter.updateData(teams)
        rvTeamList.apply {
            layoutManager = LinearLayoutManager(this@TeamsSearchFragment.context)
            adapter = teamAdapter
        }
    }

    private fun initData(query: String) {
        teamViewModel.teamsSearch?.observe(this, Observer { teams ->
            if (teams != null) {
                this.teams = teams
                setLayout()
            }
        })
        teamViewModel.loading.observe(this, Observer { loading ->
            pbTeamList.visibility = if (loading) View.VISIBLE else View.GONE
        })
        teamViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        teamViewModel.loadTeamsSearch(query)
    }
}