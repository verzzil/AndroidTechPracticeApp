package ru.itis.androidtechpracticeapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ToggleBottomNavBar {

    lateinit var navController: NavController

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModelComponent: ViewModelComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewConfiguration()
    }

    private fun setupViewConfiguration() {
        setupNavController()

        setupBottomNavigation()

        setupToolbarAndDrawer()
    }

    override fun hideBottomBar() {
        bottom_navigation.visibility = View.GONE
    }

    override fun showBottomBar() {
        bottom_navigation.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.test, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawer_layout) || super.onSupportNavigateUp()
    }

    private fun setupBottomNavigation() {
        bottom_navigation.setupWithNavController(navController)
    }

    private fun setupToolbarAndDrawer() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, drawer_layout)
        nav_view.setupWithNavController(navController)
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

}