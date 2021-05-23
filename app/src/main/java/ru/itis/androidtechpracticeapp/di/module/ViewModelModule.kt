package ru.itis.androidtechpracticeapp.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.androidtechpracticeapp.di.ViewModelKey
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrentNewsViewModel::class)
    fun bindCurrentNewViewModel(viewModel: CurrentNewsViewModel): ViewModel

}