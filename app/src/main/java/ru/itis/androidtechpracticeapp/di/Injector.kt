package ru.itis.androidtechpracticeapp.di

import android.app.Application
import ru.itis.androidtechpracticeapp.di.component.AppComponent
import ru.itis.androidtechpracticeapp.di.component.DaggerAppComponent

object Injector {
    private lateinit var appComponent: AppComponent

    fun init(app: Application) {
        appComponent = DaggerAppComponent.builder()
            .appModule(app)
            .build()
    }

    fun viewModelComponent() =
        appComponent.viewModelComponent().create()

}