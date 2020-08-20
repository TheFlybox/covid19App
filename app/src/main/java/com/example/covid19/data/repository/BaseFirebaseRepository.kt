package com.example.covid19.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

abstract class BaseFirebaseRepository<T>() : IBaseRepositoryOperation<T>{
    private var database: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var ref: CollectionReference

    init {
        ref = database.collection(this.getRootCollection())
    }

    abstract fun getRootCollection(): String
    fun getRef(): CollectionReference{ return ref }
}