package com.example.covid19.data.mapper

import com.example.covid19.data.model.CovidMenu

class CovidMenuMapper: Mapper<CovidMenu>() {

    override fun toObject(item: MutableMap<*, *>): CovidMenu {
        return mapper.convertValue(item, CovidMenu::class.java)
    }

    override fun toMap(item: CovidMenu): MutableMap<*, *> {
        return mapper.convertValue(item, MutableMap::class.java)
    }
}