package ru.itis.androidtechpracticeapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> AppCompatActivity.viewModels(
    factory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, factory).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.viewModels(
    factory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, factory).get(VM::class.java)