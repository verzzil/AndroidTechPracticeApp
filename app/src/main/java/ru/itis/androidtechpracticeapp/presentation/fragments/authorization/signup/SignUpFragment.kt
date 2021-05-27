package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_sign_up.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.AuthActivity
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import java.lang.Exception
import javax.inject.Inject


class SignUpFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: SignUpViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AuthActivity).viewModelComponent.inject(this)
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

        initObservers()
        initListeners()

    }

    private fun initObservers() {
        viewModel.getUserResp().observe(viewLifecycleOwner, {
            Toast.makeText(activity as AuthActivity, "Успешная регистрация", Toast.LENGTH_SHORT)
                .show()
            val tabhost = (activity as AuthActivity).findViewById<TabLayout>(R.id.auth_tab_layout)
            tabhost.getTabAt(0)?.select()
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as AuthActivity), "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initListeners() {
        val firstName = sign_up_et_first_name
        val lastName = sign_up_et_last_name
        val password = sign_up_et_password
        val email = sign_up_email

        btn_sign_up.setOnClickListener {
            if (firstName.text.isEmpty() || lastName.text.isEmpty() || password.text.isEmpty() || email.text.isEmpty()) {
                Toast.makeText(activity as AuthActivity,
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