package ru.itis.androidtechpracticeapp.presentation.fragments.admin

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_admin.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.AdminFrAdapter
import ru.itis.androidtechpracticeapp.utils.Key
import java.lang.Exception
import javax.inject.Inject

class AdminFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var adminFrAdapter: AdminFrAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AdminViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AdminViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initListeners()

        initUi()
    }

    private fun initObservers() {
        viewModel.getActs().observe(viewLifecycleOwner, {
            adminFrAdapter.submitList(it)
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity), "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListeners() {
        viewModel.getModeratorActs((activity as MainActivity).sp.getInt(Key.USER_ID, 0))
    }

    private fun initUi() {
        adminFrAdapter = AdminFrAdapter {
            navController.navigate(
                AdminFragmentDirections.actionAdminFragmentToAdminDecisionFragment(
                    it.id,
                    it.type,
                    it.photoLink,
                    it.text
                )
            )
        }

        admin_rv_proofs.adapter = adminFrAdapter
    }

}