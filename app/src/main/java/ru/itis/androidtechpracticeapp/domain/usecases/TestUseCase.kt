package ru.itis.androidtechpracticeapp.domain.usecases

import ru.itis.androidtechpracticeapp.presentation.models.TestTask


class TestUseCase {

    private var tasks: List<TestTask> = listOf(
        TestTask(1, "first", "ready"),
        TestTask(2, "second", "not ready"),
        TestTask(3, "third", "ready"),
        TestTask(4, "fourth", "not ready"),
    )

    fun getTestData(): String = "testData"

    fun getTaskById(id: Int): TestTask = tasks[id - 1]

    fun getTasks() : List<TestTask> = tasks

}