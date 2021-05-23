package ru.itis.androidtechpracticeapp.di.module

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    fun anyString(): String = "asdfaiudfhpwuief"
}