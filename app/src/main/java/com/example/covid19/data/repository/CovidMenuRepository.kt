package com.example.covid19.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.covid19.data.mapper.CovidMenuMapper
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.mapper.Mapper
import com.google.android.gms.tasks.OnCompleteListener

class CovidMenuRepository: BaseFirebaseRepository<CovidMenu>() {

    private lateinit var covidMenuMapper: CovidMenuMapper

    override fun getRootCollection(): String {
        return "foods"
    }

    override fun insert(item: CovidMenu) {
        covidMenuMapper = CovidMenuMapper()
        val docId = getRef().document().id
        item.id = docId
        getRef().document(docId).set(HashMap<Any, Any>(covidMenuMapper.toMap(item)))
    }

    override fun update(item: CovidMenu) {
        TODO("Not yet implemented")
    }

    override fun delete(item: CovidMenu) {
        TODO("Not yet implemented")
    }

    override fun get(): CovidMenu {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableLiveData<List<CovidMenu>> {
        val res = MutableLiveData<List<CovidMenu>>()
        covidMenuMapper = CovidMenuMapper()
        getRef().get().addOnSuccessListener{result ->
            try {
                val temp: MutableList<CovidMenu> = ArrayList<CovidMenu>()
                for (item in result) temp.add(covidMenuMapper.toObject(item.data))
                res.value = temp;
            }catch (ex: Exception){ Log.e("XD", ex.message!!) }
        }
        return res
    }
}