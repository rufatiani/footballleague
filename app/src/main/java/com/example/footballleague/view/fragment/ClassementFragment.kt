package com.example.footballleague.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.footballleague.R
import com.example.footballleague.model.Classement
import com.example.footballleague.viewmodel.ClassementViewModel
import kotlinx.android.synthetic.main.fragment_classement.*
import org.koin.android.viewmodel.ext.android.viewModel

class ClassementFragment(private val idLeague: String) : Fragment() {
    private val classementViewModel: ClassementViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classement, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun setLayout(classements: List<Classement>) {
        var no = 0
        for (classement in classements) {
            val trClassement = TableRow(activity)
            trClassement.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)

            val tvNo = TextView(activity)
            tvNo.text = (no++ + 1).toString()
            tvNo.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvNo.setBackgroundResource(R.drawable.shape_border)
            tvNo.setPadding(5, 5, 5, 5)
            trClassement.addView(tvNo)

            val tvName = TextView(activity)
            tvName.text = classement.name
            tvName.setBackgroundResource(R.drawable.shape_border)
            tvName.setPadding(5, 5, 5, 5)
            trClassement.addView(tvName)

            val tvWin = TextView(activity)
            tvWin.text = classement.win.toString()
            tvWin.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvWin.setBackgroundResource(R.drawable.shape_border)
            tvWin.setPadding(5, 5, 5, 5)
            trClassement.addView(tvWin)

            val tvLoss = TextView(activity)
            tvLoss.text = classement.loss.toString()
            tvLoss.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvLoss.setBackgroundResource(R.drawable.shape_border)
            tvLoss.setPadding(5, 5, 5, 5)
            trClassement.addView(tvLoss)

            val tvDraw = TextView(activity)
            tvDraw.text = classement.draw.toString()
            tvDraw.textAlignment = View.TEXT_ALIGNMENT_CENTER
            tvDraw.setBackgroundResource(R.drawable.shape_border)
            tvDraw.setPadding(5, 5, 5, 5)
            trClassement.addView(tvDraw)

            tlClassement.addView(trClassement, no)
        }
    }

    private fun initData() {
        classementViewModel.classement.observe(this, Observer { classements ->
            if (classements != null) {
//                this.classements = classements
                setLayout(classements)
            }
        })

        classementViewModel.loading.observe(this, Observer { loading ->
            //            pbEventList.visibility = if (loading) View.VISIBLE else View.GONE
        })
        classementViewModel.error.observe(this, Observer { msgError ->
            Toast.makeText(activity, msgError, Toast.LENGTH_SHORT).show()
        })
        classementViewModel.loadClassement(idLeague)
    }

}