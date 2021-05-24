package ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sign_in.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.AuthActivity
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsViewModel
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class SignInFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as AuthActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSubscribes()
        initListeners()
    }

    private fun initSubscribes() {
        viewModel.getUserName().observe(viewLifecycleOwner, {
            (activity as AuthActivity).sp.edit().apply {
                putString(Key.USER_NAME, it)
                apply()
            }
        })
        viewModel.getToken().observe(viewLifecycleOwner, {
            (activity as AuthActivity).sp.edit().apply {
                putInt(Key.USER_ID, it.userId)
                putString(Key.TOKEN, it.token)
                apply()
            }
            startActivity(
                Intent((activity as AuthActivity), MainActivity::class.java)
            )
            (activity as AuthActivity).finish()
        })
    }

    private fun initListeners() {
        sign_in_btn.setOnClickListener {
            viewModel.signIn(sign_in_et_login.text.toString(), sign_in_et_password.text.toString())
        }
    }

}