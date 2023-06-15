package com.ifkusyoba.compose_app.di

import com.ifkusyoba.compose_app.data.remote.ApiConfig
import com.ifkusyoba.compose_app.data.repository.Repository

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.startApiService()
        return Repository.getInstance(apiService)
    }
}