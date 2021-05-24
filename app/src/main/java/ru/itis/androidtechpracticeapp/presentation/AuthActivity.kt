package ru.itis.androidtechpracticeapp.presentation

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_auth.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.di.Injector
import ru.itis.androidtechpracticeapp.di.component.ViewModelComponent
import ru.itis.androidtechpracticeapp.presentation.adapters.FragmentViewPagerAdapter
import ru.itis.androidtechpracticeapp.utils.Consts
import ru.itis.androidtechpracticeapp.utils.Key

class AuthActivity : AppCompatActivity() {

    lateinit var viewModelComponent: ViewModelComponent

    lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        sp = getSharedPreferences(Consts.SP_NAME, MODE_PRIVATE)

        initUi()
    }

    private fun initUi() {
        initPagerAdapter()

        initTabLayoutMediator()
    }

    private fun initTabLayoutMediator() {
        TabLayoutMediator(auth_tab_layout, auth_view_pager) { tab, position ->
            if (position == 0) {
                tab.text = "SIGN IN"
            } else {
                tab.text = "SIGN UP"
            }
        }.attach()
    }

    private fun initPagerAdapter() {
        auth_view_pager.adapter = FragmentViewPagerAdapter(this)
    }
}