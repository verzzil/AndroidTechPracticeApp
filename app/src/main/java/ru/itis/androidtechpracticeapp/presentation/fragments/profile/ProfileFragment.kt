package ru.itis.androidtechpracticeapp.presentation.fragments.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import javax.inject.Inject

class ProfileFragment : Fragment() {

    val args: ProfileFragmentArgs by navArgs()

    private lateinit var adapterSocialLink: SocialLinkAdapter
    private lateinit var navController: NavController
    private lateinit var viewModel: ProfileViewModel

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
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentUser().observe(viewLifecycleOwner, {
//            profile_user_image todo
            profile_user_full_name.text = it.getFullName()
            adapterSocialLink.submitList(it.socialLinks)
        })
        viewModel.getChatId().observe(viewLifecycleOwner, {
            navController.navigate(ProfileFragmentDirections.actionProfileFragmentToCurrentChatFragment(it, chatType = "TWO"))
        })

        viewModel.findUser(args.userId)
        profile_send_message.setOnClickListener {
            viewModel.createChat((activity as MainActivity).sp.getInt(Key.USER_ID, 0), args.userId)
        }

        if (args.userId == (activity as MainActivity).sp.getInt(Key.USER_ID, 0)) {
            profile_send_message.visibility = View.GONE
        }

        adapterSocialLink = SocialLinkAdapter()
        profile_rv_links.adapter = adapterSocialLink
    }

}