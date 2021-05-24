package ru.itis.androidtechpracticeapp.presentation.fragments.mytasks

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_send_act_proof.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.fragments.profile.ProfileFragmentArgs
import javax.inject.Inject

class SendActProofFragment : Fragment() {

    val args: SendActProofFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var navController: NavController

    companion object {
        fun newInstance() = SendActProofFragment()
    }

    private lateinit var viewModel: SendActProofViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SendActProofViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_send_act_proof, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_act_proof_coords.setOnClickListener {
//            navController
        }

    }

}