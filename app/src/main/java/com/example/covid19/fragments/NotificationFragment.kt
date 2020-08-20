package com.example.covid19.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid19.R
import com.example.covid19.adapters.CovidNotificationRecyclerViewAdapter
import com.example.covid19.viewModels.CovidMenuViewModel
import com.example.covid19.viewModels.CovidNotificationViewModel
import kotlinx.android.synthetic.main.fragment_menu.view.*
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_notification.view.*

class NotificationFragment : Fragment() {

    private lateinit var model: CovidNotificationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)
        model = ViewModelProvider(this).get(CovidNotificationViewModel::class.java)
        val adapter = CovidNotificationRecyclerViewAdapter(view.context)
        view.notification_recyclerview.layoutManager = LinearLayoutManager(view.context)
        view.notification_recyclerview.adapter = adapter
        view.notification_recyclerview.addItemDecoration(DividerItemDecoration(view.notification_recyclerview.context, DividerItemDecoration.VERTICAL))
        model.getAllCovidNotifications().observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        return view
    }
}