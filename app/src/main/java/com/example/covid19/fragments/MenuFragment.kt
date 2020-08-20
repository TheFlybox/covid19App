package com.example.covid19.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.activities.CovidMenuDetailActivity
import com.example.covid19.adapters.MenuRecyclerViewAdapter
import com.example.covid19.adapters.MenuRecyclerViewItemClickListener
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.viewModels.CovidMenuViewModel
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.view.*

class MenuFragment : Fragment() {
    private lateinit var model: CovidMenuViewModel;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_menu, container, false)

        model = ViewModelProvider(this).get(CovidMenuViewModel::class.java)

        val adapter = MenuRecyclerViewAdapter(view.context)
        adapter.setOnItemClickListener(object : MenuRecyclerViewItemClickListener {
            override fun onClick(item: CovidMenu, pos: Int) {
                val intent = Intent(view.context, CovidMenuDetailActivity::class.java)
                intent.putExtra("CovidMenu", item)
                startActivity(intent)
            }
        })

        model.getAllCovidMenus().observe(viewLifecycleOwner, Observer { adapter.setData(it) })

        view.menuRecyclerView.layoutManager = LinearLayoutManager(view.context)
        view.menuRecyclerView.setHasFixedSize(true)
        view.menuRecyclerView.addItemDecoration(DividerItemDecoration(view.menuRecyclerView.context, DividerItemDecoration.VERTICAL))
        view.menuRecyclerView.adapter = adapter

        return view
    }
}