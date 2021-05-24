package ru.itis.androidtechpracticeapp.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.di.Injector
import ru.itis.androidtechpracticeapp.di.component.ViewModelComponent
import ru.itis.androidtechpracticeapp.presentation.fragments.mytasks.SendActProofViewModel
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

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.test, menu)
//        return true
//    }

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
//        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration(
//            setOf(R.id.news_fragment, R.id.my_tasks_fragment, R.id.top_fragment),
//            drawer_layout
//        )
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
        nav_view.setupWithNavController(navController)
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
        return super.onCreatePanelMenu(featureId, menu)
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment

        navController = navHostFragment.navController
    }

}