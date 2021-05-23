package ru.itis.androidtechpracticeapp.presentation.fragments.taskDescription

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_task_description.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.domain.usecases.TestUseCase
import ru.itis.androidtechpracticeapp.presentation.ViewModelFactory
import ru.itis.androidtechpracticeapp.presentation.fragments.myTasks.MyTasksViewModel

class TaskDescriptionFragment : Fragment() {

    companion object {
        fun newInstance() = TaskDescriptionFragment()
    }

    // Сделал Наиль
    //    @Inject
//    lateinit var factory: ViewModelProvider.Factory
//
//    private val sharedViewModel by activityViewModels<MyTasksViewModel>() { factory }
//    private val viewModel by viewModels<MyTasksViewModel>() { factory }

    private val viewModel: MyTasksViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory(
                testUseCase = TestUseCase()
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_task_description, container, false)
    }

    override fun onResume() {
        super.onResume()

        viewModel.currentTask.observe(viewLifecycleOwner, {
            tv_title_of_task.text = it.title
            tv_status_of_task.text = it.status
        })

    }

}

