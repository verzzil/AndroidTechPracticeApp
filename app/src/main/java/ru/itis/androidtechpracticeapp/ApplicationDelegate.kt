package ru.itis.androidtechpracticeapp

import android.app.Application
import ru.itis.androidtechpracticeapp.di.Injector

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }

}