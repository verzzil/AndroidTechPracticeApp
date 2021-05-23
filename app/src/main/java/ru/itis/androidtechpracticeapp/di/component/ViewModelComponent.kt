package ru.itis.androidtechpracticeapp.di.component

import dagger.Subcomponent
import ru.itis.androidtechpracticeapp.di.ActivityScope
import ru.itis.androidtechpracticeapp.di.module.ViewModelModule
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsFragment

@Subcomponent(modules = [ViewModelModule::class])
@ActivityScope
interface ViewModelComponent {

    fun inject(fragment: NewsFragment)
    fun inject(fragment: CurrentNewsFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}