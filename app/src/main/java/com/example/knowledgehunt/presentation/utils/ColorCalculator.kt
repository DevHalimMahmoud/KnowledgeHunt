package com.example.knowledgehunt.presentation.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.knowledgehunt.presentation.theme.*

fun calculateIncrement5(level: Long?): Int {
    when {
        level == null -> {
            return Color.LightGray.toArgb()
        }
        level <= 5 -> {
            return lvl1.toArgb()
        }
        level <= 10 -> {
            return lvl2.toArgb()
        }
        level <= 15 -> {
            return lvl3.toArgb()
        }
        level <= 20 -> {
            return lvl4.toArgb()
        }
        level <= 25 -> {
            return lvl5.toArgb()
        }
        level <= 30 -> {
            return lvl6.toArgb()
        }
        level <= 35 -> {
            return lvl7.toArgb()
        }
        level <= 40 -> {
            return lvl8.toArgb()
        }
        level <= 45 -> {
            return lvl9.toArgb()
        }
        level <= 50 -> {
            return lvl10.toArgb()
        }
        else -> {
            return lvl10.toArgb()
        }
    }
}

fun calculateIncrement10(level: Long?): Int {
    when {
        level == null -> {
            return Color.LightGray.toArgb()
        }
        level <= 10 -> {
            return lvl1.toArgb()
        }
        level <= 20 -> {
            return lvl2.toArgb()
        }
        level <= 30 -> {
            return lvl3.toArgb()
        }
        level <= 40 -> {
            return lvl4.toArgb()
        }
        level <= 50 -> {
            return lvl5.toArgb()
        }
        level <= 60 -> {
            return lvl6.toArgb()
        }
        level <= 70 -> {
            return lvl7.toArgb()
        }
        level <= 80 -> {
            return lvl8.toArgb()
        }
        level <= 90 -> {
            return lvl9.toArgb()
        }
        level <= 100 -> {
            return lvl10.toArgb()
        }
        else -> {
            return lvl10.toArgb()
        }
    }
}

fun calculateIncrement1(level: Long?): Int {
    when {
        level == null -> {
            return Color.LightGray.toArgb()
        }
        level <= 1 -> {
            return lvl1.toArgb()
        }
        level <= 2 -> {
            return lvl2.toArgb()
        }
        level <= 3 -> {
            return lvl3.toArgb()
        }
        level <= 4 -> {
            return lvl4.toArgb()
        }
        level <= 5 -> {
            return lvl5.toArgb()
        }
        level <= 6 -> {
            return lvl6.toArgb()
        }
        level <= 7 -> {
            return lvl7.toArgb()
        }
        level <= 8 -> {
            return lvl8.toArgb()
        }
        level <= 9 -> {
            return lvl9.toArgb()
        }
        level <= 10 -> {
            return lvl10.toArgb()
        }
        else -> {
            return lvl10.toArgb()
        }
    }
}

fun calculateIncrement0orMore(level: Long?): Int {
    return when {
        level == null -> {
            Color.Gray.toArgb()
        }
        level <= 0 -> {
            Color.Gray.toArgb()
        }
        else -> {
            green.toArgb()
        }
    }
}

fun calculate0LessOrMore(level: Long?): Int {
    return when {
        level == null -> {
            Color.Gray.toArgb()
        }
        level < 0 -> {
            lvl9.toArgb()
        }
        level > 0 -> {
            green.toArgb()
        }
        else -> {
            Color.Gray.toArgb()
        }
    }
}

fun calculateIncrement50(level: Long?): Int {
    return when {
        level == null -> {
            Color.Gray.toArgb()
        }
        level <= 0 -> {
            Color.Gray.toArgb()
        }
        level <= 50 -> {
            green.toArgb()
        }
        level <= 100 -> {
            lvl6.toArgb()
        }
        level <= 150 -> {
            lvl7.toArgb()
        }
        else -> {
            lvl8.toArgb()
        }
    }
}