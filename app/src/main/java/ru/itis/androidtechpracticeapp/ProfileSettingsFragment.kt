package ru.itis.androidtechpracticeapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.NavController
import kotlinx.android.synthetic.main.fragment_profile_settings.*
import ru.itis.androidtechpracticeapp.data.api.dto.ProfileSettingsDto
import ru.itis.androidtechpracticeapp.data.api.dto.SocialLinkDto
import ru.itis.androidtechpracticeapp.presentation.MainActivity
import ru.itis.androidtechpracticeapp.presentation.fragments.messages.ChatsViewModel
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class ProfileSettingsFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileSettingsFragment()
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ProfileSettingsViewModel
    private lateinit var navController: NavController
    private lateinit var selectedItem: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).viewModelComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(ProfileSettingsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = (activity as MainActivity).navController
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        viewModel.getErrorMessages().observe(viewLifecycleOwner, {
            Toast.makeText((activity as MainActivity), it, Toast.LENGTH_LONG).show()
        })
        viewModel.getChangeReady().observe(viewLifecycleOwner, {
            if (it == true) {
                Toast.makeText((activity as MainActivity), "Настройки изменены!", Toast.LENGTH_SHORT).show()
            }
        })

        profile_settings_btn.setOnClickListener {
            viewModel.changeUserSettings(buildProfileSettingsDto(),
                (activity as MainActivity).sp.getInt(Key.USER_ID, 0))
        }

    }

    private fun buildProfileSettingsDto() = ProfileSettingsDto(
        id = null,
        firstName = profile_settings_et_first_name.text.toString(),
        lastName = profile_settings_et_last_name.text.toString(),
        oldPassword = profile_settings_et_old_password.text.toString(),
        newPassword = profile_settings_et_new_password.text.toString(),
        socialLinks = listOf(
            SocialLinkDto(
                id = null,
                userId = (activity as MainActivity).sp.getInt(Key.USER_ID, 0),
                titleOfSocialRecourse = selectedItem,
                socialLink = profile_settings_anchor_social_links_link.text.toString()
            )
        ),
        photoLink = profile_settings_et_link.text.toString()
    )

    private fun initUi() {
        initSpinner()
    }

    private fun initSpinner() {
        val spinnerArrayList = arrayListOf("Соц сеть", "VK", "Facebook", "Instagram")
        val spinnerAdapter = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_spinner_item,
            spinnerArrayList
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        profile_settings_anchor_social_links_spinner.adapter = spinnerAdapter
        profile_settings_anchor_social_links_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                selectedItem = parent?.getAdapter()?.getItem(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }


}