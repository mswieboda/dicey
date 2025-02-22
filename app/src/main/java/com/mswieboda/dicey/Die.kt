package com.mswieboda.dicey

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

enum class Die(
    val value: Int,
    val displaySymbol: String,
    @DrawableRes val svgResourceId: Int
) {
    One(1, "⚀", R.drawable.pip_one),
    Two(2, "⚁", R.drawable.pip_two),
    Three(3, "⚂", R.drawable.pip_three),
    Four(3, "⚃", R.drawable.pip_four),
    Five(3, "⚄", R.drawable.pip_five),
    Six(3, "⚅", R.drawable.pip_six);

    @Composable
    fun painter(): Painter {
        return painterResource(svgResourceId)
    }
}
