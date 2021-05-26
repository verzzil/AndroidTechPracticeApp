package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.graphics.Color
import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_create_act.*
import kotlinx.android.synthetic.main.fragment_my_tasks.*
import kotlinx.android.synthetic.main.fragment_news.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.ToggleBars
import ru.itis.androidtechpracticeapp.presentation.adapters.ActsAdapter
import ru.itis.androidtechpracticeapp.presentation.adapters.AddToGroupAdapter
import ru.itis.androidtechpracticeapp.presentation.adapters.AddedToGroupAdapter
import ru.itis.androidtechpracticeapp.presentation.itemDecorators.NewsItemDecorator
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject


class CreateActFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var navController: NavController

    private lateinit var viewModel: CreateActViewModel
    private lateinit var rvAddToGroupAdapter: AddToGroupAdapter
    private lateinit var rvAdded: AddedToGroupAdapter
    private lateinit var toggleBars: ToggleBars

    private var chooseUsersIds: List<Int> = ArrayList()
    private var action = "USER"

    companion object {
        fun newInstance() = CreateActFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        toggleBars = (activity as MainActivity)
        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(CreateActViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_create_act, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        initUi()
    }

    override fun onPause() {
        super.onPause()
        toggleBars.showBottomBar()
    }

    private fun initObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, {
            rvAddToGroupAdapter.submitList(it)
        })

        viewModel.getSelectedUsers().observe(viewLifecycleOwner, {
            chooseUsersIds = it.toList().map { userPresentation -> userPresentation.id }
            rvAdded.submitList(it.toList())
        })
    }

    private fun initListeners() {
        create_act_floating_btn.setOnClickListener {
            (chooseUsersIds as ArrayList).add((activity as MainActivity).sp.getInt(Key.USER_ID, 0))
            if (action == "USER") {
                viewModel.createUserAct(
                    (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                    create_act_title.text.toString()
                )
                (activity as MainActivity).onBackPressed()
            } else {
                if (chooseUsersIds.size > 2) {
                    viewModel.createGroupAct(
                        chooseUsersIds,
                        (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                        create_act_title.text.toString()
                    )
                    (activity as MainActivity).onBackPressed()
                } else {
                    Toast.makeText((activity as MainActivity),
                        "Выберете своих единомышленников (больше одного)",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        create_act_search_user.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean =
                    false

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null && newText != "")
                        viewModel.findUsers(newText)
                    return true
                }
            }
        )
    }

    private fun initUi() {

        toggleBars.hideBottomBar()

        create_act_change_user.setTextColor(Color.BLUE)

        create_act_change_user.setOnClickListener {
            if ((it as TextView).currentTextColor != Color.BLUE) {
                it.setTextColor(Color.BLUE)
                create_act_change_group.setTextColor(Color.LTGRAY)
                action = "USER"

                create_act_search_user.visibility = View.INVISIBLE
                create_act_search_results.visibility = View.INVISIBLE
                create_act_results_remove.visibility = View.INVISIBLE
            }
        }
        create_act_change_group.setOnClickListener {
            if ((it as TextView).currentTextColor != Color.BLUE) {
                it.setTextColor(Color.BLUE)
                create_act_change_user.setTextColor(Color.LTGRAY)
                action = "GROUP"

                create_act_search_user.visibility = View.VISIBLE
                create_act_search_results.visibility = View.VISIBLE
                create_act_results_remove.visibility = View.VISIBLE
            }
        }

        rvAddToGroupAdapter = AddToGroupAdapter {
            viewModel.setSelectedUsers(it)
        }
        rvAdded = AddedToGroupAdapter {
            viewModel.removeSelectedUser(it)
        }
        create_act_search_results.adapter = rvAddToGroupAdapter

        create_act_results_remove.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        create_act_results_remove.addItemDecoration(NewsItemDecorator(20))
        create_act_results_remove.adapter = rvAdded
    }

}