package ru.itis.androidtechpracticeapp.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.Provides
import ru.itis.androidtechpracticeapp.data.db.dao.MessageDao
import ru.itis.androidtechpracticeapp.data.db.dao.PostDao
import ru.itis.androidtechpracticeapp.data.db.dao.UserDao
import ru.itis.androidtechpracticeapp.data.repositories.PostsRepository
import ru.itis.androidtechpracticeapp.di.module.*
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        RepoModule::class,
        ViewModelFactoryModule::class,
        DbModule::class,
        DomainModule::class
    ]
)
interface AppComponent {

    fun viewModelComponent(): ViewModelComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appModule(application: Application): Builder

        fun build(): AppComponent
    }
}