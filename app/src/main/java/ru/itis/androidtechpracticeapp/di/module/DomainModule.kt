package ru.itis.androidtechpracticeapp.di.module

import dagger.Module
import dagger.Provides
import ru.itis.androidtechpracticeapp.data.repositories.ChatRepository
import ru.itis.androidtechpracticeapp.data.repositories.PostsRepository
import ru.itis.androidtechpracticeapp.data.repositories.UsersRepository
import ru.itis.androidtechpracticeapp.domain.usecases.*
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePostUseCase(
        postsRepository: PostsRepository
    ): PostUseCase =
        PostUseCaseImpl(postsRepository)

    @Provides
    @Singleton
    fun provideChatUseCase(
        chatRepository: ChatRepository
    ): ChatUseCase =
        ChatUseCaseImpl(chatRepository)

    @Provides
    @Singleton
    fun provideUserUseCase(
        usersRepository: UsersRepository
    ): UserUseCase =
        UserUseCaseImpl(usersRepository)

}