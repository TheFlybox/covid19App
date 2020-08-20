package com.example.covid19.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.repository.CovidMenuRepository

class CovidMenuViewModel() : ViewModel() {
    private var repository: CovidMenuRepository = CovidMenuRepository()
    private var allCovidMenus: LiveData<List<CovidMenu>>

    init {
        allCovidMenus = repository.getAll()
    }

    fun getAllCovidMenus(): LiveData<List<CovidMenu>>{
        return allCovidMenus
    }
    fun insert(item: CovidMenu){
        repository.insert(item)
    }
}