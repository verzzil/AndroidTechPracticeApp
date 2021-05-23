package ru.itis.androidtechpracticeapp.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.itis.androidtechpracticeapp.utils.AppViewModelFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}