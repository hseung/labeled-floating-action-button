package com.buzzvil.labeledfab

import androidx.annotation.DrawableRes

class LabeledFloatingActionButtonConfig(
    @DrawableRes val icon: Int = 0,
    val labelText: String = "",
    val labelTextSizePx: Int = 0,
    val labelTextColor: Int = 0,
    val labelTextPadding: Int = 0,
    val labelPosition: LabelPosition = LabelPosition.BOTTOM
)
