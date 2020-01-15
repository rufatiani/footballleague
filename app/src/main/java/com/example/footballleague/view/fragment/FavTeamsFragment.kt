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
import com.example.footballleague.adapter.TeamFavoriteAdapter
import com.example.footballleague.model.Team
import com.example.footballleague.utils.Const
import com.example.footballleague.view.activity.DetailTeamActivity
import com.example.footballleague.viewmodel.TeamViewModel
import kotlinx.android.synthetic.main.fragment_teams.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavTeamsFragment : Fragment(){
    private lateinit var teamFavoriteAdapter: TeamFavoriteAdapter
    private val teamViewModel: TeamViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        svTeams.visibility = View.GONE
        setLayout()
        initData()
    }

    private fun setLayout() {
        rvTeamList.apply {
            layoutManager = LinearLayoutManager(this@FavTeamsFragment.context)
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    private val teams = Observer<List<Team>> { teams ->
        if (teams != null) {
            pbTeamList.visibility = View.VISIBLE
            teamFavoriteAdapter = TeamFavoriteAdapter { itemSelected: HashMap<String, Any> ->
                if (itemSelected[Const.PARCEL_ACTION]!!.equals(Const.PARCEL_CLICK)) {
                    val intent = Intent(activity, DetailTeamActivity::class.java)
                    intent.putExtra(Const.PARCEL_TEAM, itemSelected[Const.PARCEL_TEAM] as Team)
                    startActivity(intent)
                } else {
                    showDeleteDialogue(itemSelected[Const.PARCEL_TEAM] as Team)?.show()
                }
            }

            teamFavoriteAdapter.updateData(teams)
            rvTeamList.adapter = teamFavoriteAdapter

            pbTeamList.visibility = View.GONE
            rvTeamList.adapter = teamFavoriteAdapter
            teamFavoriteAdapter.notifyDataSetChanged()
        }
    }

    private fun initData() {
        teamViewModel.teamsFav?.observe(viewLifecycleOwner, this.teams)

        teamViewModel.loading.observe(this, Observer { loading ->
            pbTeamList.visibility = if (loading) View.VISIBLE else View.GONE
        })
        teamViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        teamViewModel.loadFavTeams()
    }

    private fun showDeleteDialogue(team: Team): AlertDialog.Builder? {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle(resources.getString(R.string.var_msg_title))
        builder?.setMessage(resources.getString(R.string.var_msg))

        builder?.setPositiveButton(android.R.string.yes) { dialog, which ->
            teamViewModel.deleteFavTeam(team.idTeam)
            initData()
        }

        builder?.setNegativeButton(android.R.string.no) { dialog, which ->
            builder.setCancelable(true)
        }

        return builder
    }
}