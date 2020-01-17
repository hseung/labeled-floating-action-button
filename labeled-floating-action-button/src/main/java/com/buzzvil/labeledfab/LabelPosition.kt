package com.buzzvil.labeledfab

enum class LabelPosition(val value: Int) {
    TOP(0),
    BOTTOM(1);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }
}