package ru.itis.androidtechpracticeapp.presentation.fragments.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.adapters.SocialLinkAdapter
import ru.itis.androidtechpracticeapp.presentation.adapters.TopUsersAdapter
import ru.itis.androidtechpracticeapp.presentation.fragments.topusers.TopUsersViewModel
import ru.itis.androidtechpracticeapp.utils.Key
import java.lang.Exception
import javax.inject.Inject

class ProfileFragment : Fragment() {

    val args: ProfileFragmentArgs by navArgs()

    private lateinit var adapterSocialLink: SocialLinkAdapter
    private lateinit var navController: NavController
    private lateinit var viewModel: ProfileViewModel
    private var currentId = -1

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        navController = (activity as MainActivity).navController
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.userId == -1) currentId = (activity as MainActivity).sp.getInt(Key.USER_ID, 0)
        else currentId = args.userId

        initObservers()
        initListeners()

        initUi()
    }

    private fun initListeners() {
        viewModel.findUser(currentId)
        profile_send_message.setOnClickListener {
            viewModel.createChat((activity as MainActivity).sp.getInt(Key.USER_ID, 0), currentId)
        }
    }

    private fun initObservers() {
        viewModel.getCurrentUser().observe(viewLifecycleOwner, {
            if (it.bitmap != null)
                profile_user_image.setImageBitmap(it.bitmap)
            profile_user_full_name.text = it.getFullName()
            adapterSocialLink.submitList(it.socialLinks)
        })
        viewModel.getChatId().observe(viewLifecycleOwner, {
            navController.navigate(ProfileFragmentDirections.actionProfileFragmentToCurrentChatFragment(
                it,
                chatType = "TWO"))
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity), "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        })
    }

    private fun initUi() {
        if (currentId == (activity as MainActivity).sp.getInt(Key.USER_ID, 0)) {
            profile_send_message.visibility = View.GONE
        }

        adapterSocialLink = SocialLinkAdapter {
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(it.socialLink))
            )
        }
        profile_rv_links.adapter = adapterSocialLink
    }

}