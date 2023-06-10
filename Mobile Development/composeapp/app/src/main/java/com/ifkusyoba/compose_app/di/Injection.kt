package com.ifkusyoba.compose_app.di

import com.ifkusyoba.compose_app.data.repository.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}