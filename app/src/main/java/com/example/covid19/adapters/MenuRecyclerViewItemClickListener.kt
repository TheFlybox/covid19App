package com.example.covid19.adapters

import com.example.covid19.data.model.CovidMenu

interface MenuRecyclerViewItemClickListener {
    fun onClick(item: CovidMenu, pos: Int)
}