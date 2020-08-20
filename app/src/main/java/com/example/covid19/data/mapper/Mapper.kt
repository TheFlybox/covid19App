package com.example.covid19.data.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlin.collections.HashMap

abstract class Mapper<G> {
    var mapper: ObjectMapper = ObjectMapper().registerKotlinModule()
    abstract fun toObject(item: MutableMap<*, *>): G
    abstract fun toMap(item: G): MutableMap<*, *>
}