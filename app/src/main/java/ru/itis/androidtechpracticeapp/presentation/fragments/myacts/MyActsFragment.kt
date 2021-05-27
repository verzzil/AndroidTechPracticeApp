package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_my_tasks.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.ActsAdapter
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class MyActsFragment : Fragment() {

    companion object {
        fun newInstance() = MyActsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var navController: NavController

    lateinit var viewModel: MyActsViewModel
    lateinit var rvTasksAdapter: ActsAdapter
//    private val viewModel: MyTasksViewModel by activityViewModels(
//        factoryProducer = {
//            ViewModelFactory(
//                testUseCase = TestUseCase()
//            )
//        }
//    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MyActsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_my_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = (activity as MainActivity).sp.getInt(Key.USER_ID, 0)

        initSubscribes()
        initListeners(userId)

        initUi()

    }

    private fun initSubscribes() {
        viewModel.getList().observe(viewLifecycleOwner, {
            rvTasksAdapter.submitList(it)
        })
    }

    private fun initListeners(userId: Int) {
        viewModel.searchActs(userId)
        my_tasks_change_all.setOnClickListener {
            if ((it as TextView).currentTextColor != Color.BLUE) {
                it.setTextColor(Color.BLUE)
                my_tasks_change_continue.setTextColor(Color.LTGRAY)
                my_tasks_change_end.setTextColor(Color.LTGRAY)
                viewModel.searchActs(userId)
            }
        }
        my_tasks_change_continue.setOnClickListener {
            if ((it as TextView).currentTextColor != Color.BLUE) {
                it.setTextColor(Color.BLUE)
                my_tasks_change_all.setTextColor(Color.LTGRAY)
                my_tasks_change_end.setTextColor(Color.LTGRAY)
                viewModel.searchContinueActs(userId)
            }
        }
        my_tasks_change_end.setOnClickListener {
            if ((it as TextView).currentTextColor != Color.BLUE) {
                it.setTextColor(Color.BLUE)
                my_tasks_change_all.setTextColor(Color.LTGRAY)
                my_tasks_change_continue.setTextColor(Color.LTGRAY)
                viewModel.searchEndActs(userId)
            }
        }
    }

    private fun initUi() {
        my_tasks_change_all.setTextColor(Color.BLUE)
        initAdapter()
    }

    private fun initAdapter() {
        rvTasksAdapter = ActsAdapter {
            if (it.isAdmin == false || it.actStatus == "END") {
                if (it.actStatus == "END") {
                    showToast("Вы уже завершили этот акт")
                }
                showToast("Отправить доказательство на акт группы могут только организаторы группы")
            } else {
                navController.navigate(
                    MyActsFragmentDirections.actionMyTasksFragmentToSendActProofFragment(
                        it.id,
                        it.type
                    )
                )
            }
        }
        my_tasks_rv_task.adapter = rvTasksAdapter
    }

    private fun showToast(text: String) {
        Toast.makeText((activity as MainActivity), text, Toast.LENGTH_SHORT).show()
    }

}