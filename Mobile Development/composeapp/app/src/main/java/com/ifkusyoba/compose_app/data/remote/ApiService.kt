package com.ifkusyoba.compose_app.data.remote

import com.ifkusyoba.compose_app.data.model.AddStunting
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/new-stunting-entry")
    @FormUrlEncoded
    suspend fun addStunting(
        @Field("name") nama: String,
        @Field("gender") gender: String,
        @Field("age") age: Double,
        @Field("height") height: Double,
        @Field("weight") weight: Double,
    ): AddStunting
}