package ru.itis.androidtechpracticeapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.di.Injector
import ru.itis.androidtechpracticeapp.di.component.ViewModelComponent
import ru.itis.androidtechpracticeapp.presentation.fragments.news.NewsFragmentDirections
import ru.itis.androidtechpracticeapp.utils.Consts
import ru.itis.androidtechpracticeapp.utils.Key
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ToggleBars {

    lateinit var navController: NavController

    @Inject
    lateinit var sp: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModelComponent: ViewModelComponent

    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelComponent.inject(this)
        sharedViewModel =
            ViewModelProvider(this, viewModelFactory).get(SharedViewModel::class.java)

        sp = getSharedPreferences(Consts.SP_NAME, MODE_PRIVATE)

        initUi()

    }

    override fun hideBottomBar() {
        bottom_navigation.visibility = View.GONE
    }

    override fun showBottomBar() {
        bottom_navigation.visibility = View.VISIBLE
    }

    override fun hideActionBar() {
        app_bar.visibility = View.GONE
        nav_view.visibility = View.GONE
    }

    override fun showActionBar() {
        app_bar.visibility = View.VISIBLE
        nav_view.visibility = View.VISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawer_layout) || super.onSupportNavigateUp()
    }

    private fun initUi() {
        setupNavController()

        setupBottomNavigation()

        setupToolbarAndDrawer()
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setupWithNavController(navController)
    }

    private fun setupToolbarAndDrawer() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, drawer_layout)

        sp.getString(Key.USER_ROLE, "").let {
            if (it == "ADMIN" || it == "MODERATOR") {
                nav_view.menu.add("Admin").setOnMenuItemClickListener {
                    navController.navigate(NewsFragmentDirections.actionNewsFragmentToAdminFragment())
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                }
            }
        }
        nav_view.menu.getItem(7).setOnMenuItemClickListener {
            sp.edit().clear().apply()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return@setOnMenuItemClickListener true
        }

        nav_view.setupWithNavController(navController)
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

}