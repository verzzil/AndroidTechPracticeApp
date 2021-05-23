package ru.itis.androidtechpracticeapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.itis.androidtechpracticeapp.domain.usecases.TestUseCase
import ru.itis.androidtechpracticeapp.presentation.fragments.myTasks.MyTasksViewModel
import ru.itis.androidtechpracticeapp.presentation.fragments.topUsers.TopUsersViewModel
import java.lang.IllegalArgumentException

open class ViewModelFactory(
    private val testUseCase: TestUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MyTasksViewModel::class.java) -> {
                MyTasksViewModel(testUseCase) as? T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            modelClass.isAssignableFrom(TopUsersViewModel::class.java) -> {
                TopUsersViewModel() as? T
                    ?: throw IllegalArgumentException("Unknown ViewModel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }

}