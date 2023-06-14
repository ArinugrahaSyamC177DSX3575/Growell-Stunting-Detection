package com.ifkusyoba.compose_app.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class CardItem(
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
    @StringRes val subtitle: Int,
)