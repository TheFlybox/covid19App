package com.example.covid19.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid19.R
import com.example.covid19.data.model.CovidNotification
import kotlinx.android.synthetic.main.notification_item_layout.view.*

class CovidNotificationRecyclerViewAdapter(context: Context):
    RecyclerView.Adapter<CovidNotificationRecyclerViewAdapter.CovidNotificationRecyclerViewHolder>() {

    private var data: List<CovidNotification> = ArrayList()
    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    class CovidNotificationRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(
        itemView
    ) {
        var title: TextView = itemView.notification_title
        var date: TextView = itemView.notification_date
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CovidNotificationRecyclerViewHolder {
        val view: View = mInflater.inflate(R.layout.notification_item_layout, parent, false);
        return CovidNotificationRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CovidNotificationRecyclerViewHolder, position: Int) {
        holder.title.text = data[position].title
        holder.date.text = data[position].date
    }

    fun setData(data: List<CovidNotification>){
        this.data = data
        notifyDataSetChanged()
    }
}