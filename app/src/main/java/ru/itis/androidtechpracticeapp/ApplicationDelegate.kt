package ru.itis.androidtechpracticeapp

import android.app.Application
import com.google.firebase.FirebaseApp
import ru.itis.androidtechpracticeapp.di.Injector

class ApplicationDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        Injector.init(this)
    }

}