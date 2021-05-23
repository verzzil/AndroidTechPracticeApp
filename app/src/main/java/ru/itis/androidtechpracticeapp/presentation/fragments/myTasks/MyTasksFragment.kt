package ru.itis.androidtechpracticeapp.presentation.fragments.myTasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kotlinx.android.synthetic.main.fragment_my_tasks.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.domain.usecases.TestUseCase
import ru.itis.androidtechpracticeapp.presentation.ViewModelFactory
import ru.itis.androidtechpracticeapp.presentation.adapters.CurrentTasksAdapter

class MyTasksFragment : Fragment() {

    companion object {
        fun newInstance() = MyTasksFragment()
    }

    private val viewModel: MyTasksViewModel by activityViewModels(
        factoryProducer = {
            ViewModelFactory(
                testUseCase = TestUseCase()
            )
        }
    )

    private lateinit var rvAdapter: CurrentTasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_my_tasks, container, false)
    }

    override fun onResume() {
        super.onResume()



        with(viewModel) {

            rvData.observe(viewLifecycleOwner, {
                rvAdapter = CurrentTasksAdapter(
                    it
                ) {
                    viewModel.updateCurrentTask(it.id)
//                    (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_taskDescriptionFragment)
                }
            })

        }

        viewModel.onGetRvData()

        rv_current_task.adapter = rvAdapter

    }

}