package com.example.covid19.data.repository

import androidx.lifecycle.MutableLiveData

interface IBaseRepositoryOperation<T> {
    fun insert(item: T)
    fun update(item: T)
    fun delete(item: T)
    fun get(): T
    fun getAll(): MutableLiveData<List<T>>
}