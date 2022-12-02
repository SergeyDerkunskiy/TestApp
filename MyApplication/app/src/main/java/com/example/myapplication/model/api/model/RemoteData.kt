package com.example.myapplication.model.api.model

import com.squareup.moshi.Json

data class RemoteData(
    @Json(name = "link") val link: String,
    @Json(name = "home") val home: String
)