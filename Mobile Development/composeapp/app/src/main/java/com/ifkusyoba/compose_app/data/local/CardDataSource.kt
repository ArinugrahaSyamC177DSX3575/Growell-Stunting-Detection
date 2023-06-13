package com.ifkusyoba.compose_app.data.local

import com.ifkusyoba.compose_app.R
import com.ifkusyoba.compose_app.data.model.CardItem

object CardDataSource {
    val cardData = listOf(
        CardItem(
            icon = R.drawable.growell_hamil,
            title = R.string.card_title1,
            subtitle = R.string.card_subtitle1,
        ),
        CardItem(
            icon = R.drawable.growell_lahir,
            title = R.string.card_title2,
            subtitle = R.string.card_subtitle2,
        ),
        CardItem(
            icon = R.drawable.growell_6bulan,
            title = R.string.card_title3,
            subtitle = R.string.card_subtitle3,
        ),
        CardItem(
            icon = R.drawable.growell_balita,
            title = R.string.card_title4,
            subtitle = R.string.card_subtitle4,
        ),
        CardItem(
            icon = R.drawable.growell_phbs,
            title = R.string.card_title5,
            subtitle = R.string.card_subtitle5,
        ),
        CardItem(
            icon = R.drawable.growell_gizi_remaja,
            title = R.string.card_title6,
            subtitle = R.string.card_subtitle6,
        )
    )
}