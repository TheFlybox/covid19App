package com.example.covid19.data.mapper

import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.model.CovidNotification

class CovidNotificationMapper: Mapper<CovidNotification>() {

    override fun toObject(item: MutableMap<*, *>): CovidNotification {
        return mapper.convertValue(item, CovidNotification::class.java)
    }

    override fun toMap(item: CovidNotification): MutableMap<*, *> {
        return mapper.convertValue(item, MutableMap::class.java)
    }
}