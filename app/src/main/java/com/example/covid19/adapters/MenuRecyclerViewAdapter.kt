package com.example.covid19.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covid19.R
import com.example.covid19.adapters.MenuRecyclerViewAdapter.*
import com.example.covid19.data.model.CovidMenu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.menu_recycler_view_item.view.*

class MenuRecyclerViewAdapter(context: Context) :
    RecyclerView.Adapter<MenuRecyclerViewAdapterViewHolder>() {

    private var data: List<CovidMenu> = ArrayList()
    private var mInflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var clickListener: MenuRecyclerViewItemClickListener

    inner class MenuRecyclerViewAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var image: ImageView = itemView.item_image
        var title: TextView = itemView.item_title
        var category: TextView = itemView.item_category
        var price: TextView = itemView.item_price
        var rating: RatingBar = itemView.item_rating
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuRecyclerViewAdapterViewHolder {
        val view: View = mInflater.inflate(R.layout.menu_recycler_view_item, parent, false);
        return MenuRecyclerViewAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(data.isNullOrEmpty()) 0 else data.size
    }

    override fun onBindViewHolder(holder: MenuRecyclerViewAdapterViewHolder, position: Int) {
        if(!data.isNullOrEmpty()){
            val item = data[position]
            Glide.with(mInflater.context).load(item.imageURL).circleCrop().into(holder.image)
            holder.title.text = item.name
            holder.category.text = item.category
            holder.price.text = "$" + item.price.toString()
            holder.rating.rating = item.rating.toFloat()
            clickListener.let {
                holder.itemView.setOnClickListener {clickListener.onClick(item, position)}
            }
        }
    }

    fun setData(item: List<CovidMenu>){
        data = item
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: MenuRecyclerViewItemClickListener){
        clickListener = listener
    }
}