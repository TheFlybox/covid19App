package com.example.covid19.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.model.CovidNotification
import com.example.covid19.data.repository.CovidMenuRepository
import com.example.covid19.data.repository.CovidNotificationRepository

class CovidNotificationViewModel : ViewModel() {
    private var repository: CovidNotificationRepository = CovidNotificationRepository()
    private var allCovidNotifications: LiveData<List<CovidNotification>>

    init {
        allCovidNotifications = repository.getAll()
    }

    fun getAllCovidNotifications(): LiveData<List<CovidNotification>> {
        return allCovidNotifications
    }
    fun insert(item: CovidNotification){
        repository.insert(item)
    }
}