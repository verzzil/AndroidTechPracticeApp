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
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_admin_decision.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.data.api.dto.ProofDecisionDto
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.AdminFrAdapter
import ru.itis.androidtechpracticeapp.presentation.fragments.news.CurrentNewsFragmentArgs
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class AdminDecisionFragment : Fragment() {

    val args: AdminDecisionFragmentArgs by navArgs()

    private lateinit var navController: NavController
    private lateinit var adminFrAdapter: AdminFrAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: AdminDecisionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_decision, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(AdminDecisionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.parseImage(args.link)
        admin_decision_text.text = args.text

        initObservers()
        initListeners()

    }

    private fun initListeners() {
        admin_decision_approve.setOnClickListener {
            viewModel.setDecision(
                args.actType,
                (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                args.actId,
                "APPROVED",
                admin_decision_reward.text.toString().toInt()
            )
            (activity as MainActivity).onBackPressed()
        }
        admin_decision_decline.setOnClickListener {
            viewModel.setDecision(
                args.actType,
                (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                args.actId,
                "DECLINE",
                0
            )
            (activity as MainActivity).onBackPressed()
        }
    }

    private fun initObservers() {
        viewModel.getImg().observe(viewLifecycleOwner, {
            Log.i("asdasdas","asdasd")
            admin_decision_link.setImageBitmap(it)
        })
        viewModel.getImgRes().observe(viewLifecycleOwner, {
            admin_decision_link.setImageResource(R.drawable.proof_mock)
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity),
                "Нет интернет соединения",
                Toast.LENGTH_SHORT).show()
        })
    }

}