package ru.itis.androidtechpracticeapp.di.module

import dagger.Module
import dagger.Provides
import ru.itis.androidtechpracticeapp.data.repositories.PostsRepository
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCase
import ru.itis.androidtechpracticeapp.domain.usecases.PostUseCaseImpl
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun providePostUseCase(
        postsRepository: PostsRepository
    ): PostUseCase =
        PostUseCaseImpl(postsRepository)

}