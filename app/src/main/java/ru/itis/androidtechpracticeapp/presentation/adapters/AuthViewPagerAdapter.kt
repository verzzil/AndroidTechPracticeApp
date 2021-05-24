package ru.itis.androidtechpracticeapp.presentation.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signin.SignInFragment
import ru.itis.androidtechpracticeapp.presentation.fragments.authorization.signup.SignUpFragment

class AuthViewPagerAdapter(
    fragment: FragmentActivity
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            SignInFragment()
        } else {
            SignUpFragment()
        }
    }
}