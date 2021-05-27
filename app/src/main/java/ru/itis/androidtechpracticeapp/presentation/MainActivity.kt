package ru.itis.androidtechpracticeapp.presentation

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.imageview.ShapeableImageView
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
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
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModelComponent.inject(this)
        sharedViewModel =
            ViewModelProvider(this, viewModelFactory).get(SharedViewModel::class.java)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        sp = getSharedPreferences(Consts.SP_NAME, MODE_PRIVATE)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                Log.i("asdfqw", "${task.result}")
                viewModel.saveFirebaseToken(sp.getInt(Key.USER_ID, 0), task.result)
            }
        }

        initObservers()
        initListeners()

        initUi()

    }

    private fun initListeners() {
        try {
            viewModel.findUser(sp.getInt(Key.USER_ID, 0))
        } catch (e: Exception) {
            Toast.makeText(this, "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        }

    }

    private fun initObservers() {
        viewModel.getCurrentUser().observe(this, {
            val navNewHeader = nav_view.getHeaderView(0)
            navNewHeader.findViewById<TextView>(R.id.nav_header_username).text = it.getFullName()
            navNewHeader.findViewById<TextView>(R.id.nav_header_email).text = it.email
            navNewHeader.findViewById<TextView>(R.id.nav_header_cash).text = "Cash: ${it.cash}"
            if (it.bitmap != null)
                navNewHeader.findViewById<ShapeableImageView>(R.id.nav_header_avatar)
                    .setImageBitmap(it.bitmap)
            else
                navNewHeader.findViewById<ShapeableImageView>(R.id.nav_header_avatar)
                    .setImageResource(R.drawable.mock_avatar)
        })
        viewModel.getErrors().observe(this, {
            Toast.makeText(this, "Нет интернет соединения", Toast.LENGTH_SHORT).show()
        })
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
        nav_view.menu.getItem(0).setOnMenuItemClickListener {
            navController.navigate(NewsFragmentDirections.actionNewsFragmentToProfileFragment(sp.getInt(Key.USER_ID, 0)))
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setOnMenuItemClickListener true
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