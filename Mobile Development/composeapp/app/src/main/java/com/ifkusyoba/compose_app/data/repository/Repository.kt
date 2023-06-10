package com.ifkusyoba.compose_app.data.repository

import com.ifkusyoba.compose_app.data.local.FaqDataSource
import com.ifkusyoba.compose_app.data.model.Faq
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val faqDataSource = mutableListOf<Faq>()

    init {
        if (faqDataSource.isEmpty()) {
            FaqDataSource.faq.forEach { faq ->
                faqDataSource.add(faq)
            }
        }
    }

    fun getFaqList(): Flow<List<Faq>> {
        return flowOf(faqDataSource)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository().apply { instance = this }
            }
    }
}