package ru.itis.androidtechpracticeapp.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.androidtechpracticeapp.ProfileSettingsViewModel
import ru.itis.androidtechpracticeapp.di.ViewModelKey
import ru.itis.androidtechpracticeapp.presentation.SharedViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.admin.AdminDecisionViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.admin.AdminViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin.SignInViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup.SignUpViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.map.MyMapViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.CurrentChatViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.CreateActViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.MyActsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.myacts.SendActProofViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CreatePostViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.profile.ProfileViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.topusers.TopUsersViewModel

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

    @Binds
    @IntoMap
    @ViewModelKey(ChatsViewModel::class)
    fun bindChatsViewModel(viewModel: ChatsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrentChatViewModel::class)
    fun bindCurrentChatViewModel(viewModel: CurrentChatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileSettingsViewModel::class)
    fun bindProfileSettingsViewModel(viewModel: ProfileSettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyActsViewModel::class)
    fun bindMyTasksViewModel(viewModel: MyActsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SendActProofViewModel::class)
    fun bindSendActProofViewModel(viewModel: SendActProofViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    fun bindSharedViewModel(viewModel: SharedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateActViewModel::class)
    fun bindCreateActViewModel(viewModel: CreateActViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdminViewModel::class)
    fun bindAdminViewModel(viewModel: AdminViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdminDecisionViewModel::class)
    fun bindAdminDecisionViewModel(viewModel: AdminDecisionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyMapViewModel::class)
    fun bindMyMapViewModel(viewModel: MyMapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopUsersViewModel::class)
    fun bindTopUsersViewModel(viewModel: TopUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreatePostViewModel::class)
    fun bindCreatePostViewModel(viewModel: CreatePostViewModel): ViewModel

}