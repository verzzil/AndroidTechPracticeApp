package ru.itis.androidtechpracticeapp.presentation.fragments.myTasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.itis.androidtechpracticeapp.domain.usecases.TestUseCase
import ru.itis.androidtechpracticeapp.presentation.models.TestTask
import javax.inject.Inject

class MyTasksViewModel @Inject constructor(
    private val testUseCase: TestUseCase
) : ViewModel() {

    val testData = MutableLiveData<String>()

    val rvData = MutableLiveData<List<TestTask>>()

    val currentTask = MutableLiveData<TestTask>()

    fun onGetData() {
        viewModelScope.launch {
            testUseCase.getTestData().run {
                testData.value = this
            }
        }
    }

    fun onGetRvData() {
        viewModelScope.launch {
            testUseCase.getTasks().run {
                rvData.value = this
            }
        }
    }

    fun updateCurrentTask(id: Int) {
        currentTask.value = testUseCase.getTaskById(id)
    }

}