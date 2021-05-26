package ru.itis.androidtechpracticeapp.presentation.fragments.topusers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_top_users.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.TopUsersAdapter
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsViewModel
import javax.inject.Inject

class TopUsersFragment : Fragment() {

    private lateinit var adapterTopUsers: TopUsersAdapter
    private lateinit var navController: NavController

    private lateinit var viewModel: TopUsersViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = TopUsersFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(TopUsersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_top_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTopUsers().observe(viewLifecycleOwner, {
            adapterTopUsers.submitList(it)
        })

        viewModel.findTopUsers()

        adapterTopUsers = TopUsersAdapter {
            navController.navigate(TopUsersFragmentDirections.actionTopFragmentToProfileFragment(it.id))
        }

        rv_top_users.adapter = adapterTopUsers
    }

}