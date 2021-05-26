package ru.itis.androidtechpracticeapp.di.component

import dagger.Subcomponent
import ru.itis.androidtechpracticeapp.ProfileSettingsFragment
import ru.itis.androidtechpracticeapp.di.ActivityScope
import ru.itis.androidtechpracticeapp.di.module.ViewModelModule
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.fragments.admin.AdminDecisionFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.admin.AdminFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin.SignInFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup.SignUpFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.map.MyMapFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.CurrentChatFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.CreateActFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.MyActsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.SendActProofFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CreatePostFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.profile.ProfileFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.topusers.TopUsersFragment

@Subcomponent(modules = [ViewModelModule::class])
@ActivityScope
interface ViewModelComponent {

    fun inject(fragment: NewsFragment)
    fun inject(fragment: CurrentNewsFragment)
    fun inject(fragment: ChatsFragment)
    fun inject(fragment: CurrentChatFragment)
    fun inject(fragment: SignInFragment)
    fun inject(fragment: ProfileSettingsFragment)
    fun inject(fragment: MyActsFragment)
    fun inject(fragment: SendActProofFragment)
    fun inject(activity: MainActivity)
    fun inject(fragment: CreateActFragment)
    fun inject(fragment: AdminFragment)
    fun inject(fragment: AdminDecisionFragment)
    fun inject(fragment: MyMapFragment)
    fun inject(fragment: TopUsersFragment)
    fun inject(fragment: ProfileFragment)
    fun inject(fragment: SignUpFragment)
    fun inject(fragment: CreatePostFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}