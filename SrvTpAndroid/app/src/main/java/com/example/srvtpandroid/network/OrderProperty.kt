package com.example.srvtpandroid.network

import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

data class DataProperty (
        val data: CommandsProperty
)

data class CommandsProperty (
        val commands: Array<OrderProperty>
)

data class OrderProperty (
//    val meals: Array<MealProperty>,
    val price: Double)

data class MealProperty (
    val idMeal: String,
    @Json(name = "strMeal") val name: String,
    @Json(name = "strMealThumb") val imgSrcUrl: String)