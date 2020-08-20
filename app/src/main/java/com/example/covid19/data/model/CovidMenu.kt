package com.example.covid19.data.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable

data class CovidMenu(var id: String = ""): Serializable {
    var name: String = ""
    var category: String = ""
    var description: String = ""
    var price: Double = 0.0
    var rating: Double = 0.0
    var imageURL: String = ""
}