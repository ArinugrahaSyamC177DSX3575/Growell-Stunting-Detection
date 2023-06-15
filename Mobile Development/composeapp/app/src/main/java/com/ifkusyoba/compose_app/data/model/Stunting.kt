package com.ifkusyoba.compose_app.data.model

import com.google.gson.annotations.SerializedName

data class Stunting(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("age")
    val token: String,
    @field:SerializedName("height")
    val height: String,
    @field:SerializedName("weight")
    val weight: String,
)