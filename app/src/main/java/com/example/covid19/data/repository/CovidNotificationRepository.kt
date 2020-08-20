package com.example.covid19.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.covid19.data.mapper.CovidMenuMapper
import com.example.covid19.data.mapper.CovidNotificationMapper
import com.example.covid19.data.model.CovidMenu
import com.example.covid19.data.model.CovidNotification
import com.google.firebase.firestore.Query

class CovidNotificationRepository: BaseFirebaseRepository<CovidNotification>() {

    private lateinit var notificationMapper: CovidNotificationMapper

    override fun getRootCollection(): String {
        return "notifications"
    }

    override fun insert(item: CovidNotification) {
        notificationMapper = CovidNotificationMapper()
        val doc = getRef().document()
        item.id = doc.id
        getRef().document(doc.id).set(notificationMapper.toMap(item))
    }

    override fun update(item: CovidNotification) {
        TODO("Not yet implemented")
    }

    override fun delete(item: CovidNotification) {
        TODO("Not yet implemented")
    }

    override fun get(): CovidNotification {
        TODO("Not yet implemented")
    }

    override fun getAll(): MutableLiveData<List<CovidNotification>> {
        val res = MutableLiveData<List<CovidNotification>>()
        notificationMapper = CovidNotificationMapper()
        getRef().orderBy("date", Query.Direction.DESCENDING).get().addOnSuccessListener{ result ->
            try {
                val temp: MutableList<CovidNotification> = ArrayList()
                for (item in result) temp.add(notificationMapper.toObject(item.data))
                res.value = temp;
            }catch (ex: Exception){ Log.e("XD", ex.message!!) }
        }
        return res
    }
}