package com.ifkusyoba.compose_app.data.model

import com.google.gson.annotations.SerializedName

data class AddStunting(
    @field:SerializedName("uploadResult")
    val uploadResult: Stunting,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)