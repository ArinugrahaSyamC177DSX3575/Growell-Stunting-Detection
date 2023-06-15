package com.ifkusyoba.compose_app.data.repository

import com.ifkusyoba.compose_app.data.local.CardDataSource
import com.ifkusyoba.compose_app.data.local.FaqDataSource
import com.ifkusyoba.compose_app.data.model.CardItem
import com.ifkusyoba.compose_app.data.model.Faq
import com.ifkusyoba.compose_app.data.remote.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class Repository {
    private val faqDataSource = mutableListOf<Faq>()
    private val cardDataSource = mutableListOf<CardItem>()

    init {
        if (faqDataSource.isEmpty()) {
            FaqDataSource.faq.forEach { faq ->
                faqDataSource.add(faq)
            }
        }
        if(cardDataSource.isEmpty()){
            CardDataSource.cardData.forEach{data ->
                cardDataSource.add(data)
            }
        }
    }

    fun getFaqList(): Flow<List<Faq>> {
        return flowOf(faqDataSource)
    }

    fun getRecomendationList(): Flow<List<CardItem>>{
        return flowOf(cardDataSource)
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(apiService: ApiService): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository().apply { instance = this }
            }
    }
}