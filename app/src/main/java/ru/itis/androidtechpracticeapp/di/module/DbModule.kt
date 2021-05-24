package ru.itis.androidtechpracticeapp.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.itis.androidtechpracticeapp.data.api.MyApi
import ru.itis.androidtechpracticeapp.data.db.AppDb
import ru.itis.androidtechpracticeapp.data.db.dao.MessageDao
import ru.itis.androidtechpracticeapp.data.db.dao.PostDao
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.repositories.*
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(context: Context): AppDb = Room
        .databaseBuilder(context, AppDb::class.java, "app.db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDb): UserDao =
        db.getUserDao()

    @Provides
    @Singleton
    fun providePostDao(db: AppDb): PostDao =
        db.getPostDao()

    @Provides
    @Singleton
    fun provideMessageDao(db: AppDb): MessageDao =
        db.getMessageDao()

    @Provides
    @Singleton
    fun provideUsersRepository(
        userDao: UserDao,
        myApi: MyApi
    ): UsersRepository =
        UsersRepositoryImpl(userDao, myApi)

    @Provides
    @Singleton
    fun providePostsRepository(
        usersRepository: UsersRepository,
        postDao: PostDao,
        myApi: MyApi
    ): PostsRepository =
        PostsRepositoryImpl(usersRepository, postDao, myApi)

    @Provides
    @Singleton
    fun provideChatRepository(
        myApi: MyApi
    ): ChatRepository =
        ChatRepositoryImpl(myApi)

}