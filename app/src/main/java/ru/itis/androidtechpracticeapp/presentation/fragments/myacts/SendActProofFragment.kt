package ru.itis.androidtechpracticeapp.presentation.fragments.myacts

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_send_act_proof.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.SharedViewModel
import javax.inject.Inject

class SendActProofFragment : Fragment() {

    val args: SendActProofFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var navController: NavController
    lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModel: SendActProofViewModel
    private var currentCoords: Pair<Double, Double>? = null

    companion object {
        fun newInstance() = SendActProofFragment()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel = (activity as MainActivity).sharedViewModel
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

        initObservers()
        initListeners()

    }

    private fun initObservers() {
        sharedViewModel.getCoords().observe(viewLifecycleOwner, {
            currentCoords = it
            send_act_proof_coords_indicator.text = "Координаты установлены"
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity), "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListeners() {
        send_act_proof_send.setOnClickListener {
            if (currentCoords != null && send_act_proof_link.text.isNotEmpty()) {
                viewModel.sendProof(
                    args.actId,
                    send_act_proof_link.text.toString(),
                    send_act_proof_text.text.toString(),
                    currentCoords ?: Pair(0.0, 0.0),
                    args.actType
                )
                (activity as MainActivity).onBackPressed()
            } else {
                Toast.makeText((activity as MainActivity),
                    "Ссылка на фото и координаты обязательны!",
                    Toast.LENGTH_SHORT).show()
            }
        }

        send_act_proof_coords.setOnClickListener {
            navController.navigate(SendActProofFragmentDirections.actionSendActProofFragmentToMapFragment())
        }
    }

}