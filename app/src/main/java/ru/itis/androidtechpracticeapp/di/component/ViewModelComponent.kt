package ru.itis.androidtechpracticeapp.di.component

import dagger.Subcomponent
import ru.itis.androidtechpracticeapp.di.ActivityScope
import ru.itis.androidtechpracticeapp.di.module.ViewModelModule
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin.SignInFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.CurrentChatFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsFragment

@Subcomponent(modules = [ViewModelModule::class])
@ActivityScope
interface ViewModelComponent {

    fun inject(fragment: NewsFragment)
    fun inject(fragment: CurrentNewsFragment)
    fun inject(fragment: ChatsFragment)
    fun inject(fragment: CurrentChatFragment)
    fun inject(fragment: SignInFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}