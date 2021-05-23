package ru.itis.androidtechpracticeapp.extentions

import android.content.Context

fun Context.dpToPx(dp: Int): Float =
    dp.toFloat() * this.resources.displayMetrics.density