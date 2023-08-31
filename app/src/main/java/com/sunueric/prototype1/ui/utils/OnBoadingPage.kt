package com.sunueric.prototype1.ui.utils

import androidx.annotation.DrawableRes
import com.sunueric.prototype1.R

sealed class OnBoadingPage(
    @DrawableRes val image: Int,
    @DrawableRes val progressSlider: Int,
    val discription: String

) {
    object First : OnBoadingPage(
        image = R.drawable.onboarding_1,
        progressSlider = R.drawable.slider_1,
        discription = "Empowering the visually impaired with knowledge at their fingertips. Dive into an inclusive educational experience tailored for you."
    )

    object Second : OnBoadingPage(
        image = R.drawable.onboarding_2,
        progressSlider = R.drawable.slider_2,
        discription = "Empowering the visually impaired with knowledge at their fingertips. Dive into an inclusive educational experience tailored for you."
    )

    object Third : OnBoadingPage(
        image = R.drawable.onboarding_3,
        progressSlider = R.drawable.slider_3,
        discription = "Empowering the visually impaired with knowledge at their fingertips. Dive into an inclusive educational experience tailored for you."
    )
}