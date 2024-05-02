package com.aman.mylibrary.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val courseName: Int,
    val availableCourse: Int,
    @DrawableRes val img: Int
)