package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: SignUpViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserResp().observe(viewLifecycleOwner, {
            Toast.makeText(activity as MainActivity, "Успешная регистрация", Toast.LENGTH_SHORT).show()
        })

        initUi()

    }

    private fun initUi() {
        val firstName = sign_up_et_first_name
        val lastName = sign_up_et_last_name
        val password = sign_up_et_password
        val email = sign_up_email

        btn_sign_up.setOnClickListener {
            if (firstName.text.isEmpty() || lastName.text.isEmpty() || password.text.isEmpty() || email.text.isEmpty()) {
                Toast.makeText(activity as MainActivity,
                    "Все поля должны быть заполнены",
                    Toast.LENGTH_SHORT).show()
            } else {
                viewModel.signUp(
                    firstName.text.toString(),
                    lastName.text.toString(),
                    password.text.toString(),
                    email.text.toString()
                )
            }
        }
    }

}