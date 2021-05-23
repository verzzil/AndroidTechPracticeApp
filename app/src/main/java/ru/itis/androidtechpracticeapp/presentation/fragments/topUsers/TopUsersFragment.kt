package ru.itis.androidtechpracticeapp.presentation.fragments.topUsers

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_top_users.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.TopUsersAdapter
import ru.itis.androidtechpracticeapp.presentation.fragments.profile.ProfileFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.profile.ProfileFragmentArgs
import ru.itis.androidtechpracticeapp.presentation.models.UserPresentation

class TopUsersFragment : Fragment() {

    private lateinit var adapterTopUsers: TopUsersAdapter
    private lateinit var navController: NavController

    companion object {
        fun newInstance() = TopUsersFragment()
    }

    private lateinit var viewModel: TopUsersViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = (activity as MainActivity).navController
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterTopUsers = TopUsersAdapter(UserPresentation.users) {
            navController.navigate(TopUsersFragmentDirections.actionTopFragmentToProfileFragment(it.id))
        }

        rv_top_users.adapter = adapterTopUsers
    }

}